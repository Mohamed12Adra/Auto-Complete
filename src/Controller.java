import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class Controller implements Initializable{
	@FXML
	TextArea ta;
	@FXML
	Pane pane;
	@FXML
	Button button;
	@FXML
	Button button1;
	@FXML
	ComboBox combo;
	ArrayList<String> input = new ArrayList<>();
	HashMap<String, Words> tri = new HashMap<>();
	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		try {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(null);
		Scanner in = new Scanner(file);
		while(in.hasNextLine()) {
			String line = in.nextLine();
			//System.out.println(line);
			input.add(line);
		}
		for(int i=0; i<input.size(); i++) {
		System.out.println(input.get(i).toString());	
		}
		trigram(input,tri);
		System.out.println(tri.size());
		}catch(FileNotFoundException f) {
			System.out.println("Cant Read The File");
		}
	}
	@FXML
	public void TextAreaOnAction(ActionEvent e) {
		String str = ta.getText();
		String[] arr=str.split(" ");
		String str1=arr[arr.length-2]+" "+arr[arr.length-1];
		if(tri.containsKey(str1)) {
			ArrayList<TargetWord> arrl = tri.get(str1).getTargetWord();
			Collections.sort(arrl);
			double max=0;
			for(int i=0; i<arrl.size(); i++) {
				if(max<arrl.get(i).getCount()) {
					max=arrl.get(i).getCount();
				}
			}
			for(int i=0; i<arrl.size(); i++) {
				if(max==arrl.get(i).getCount()) {
					combo.getItems().add(arrl.get(i).getStr());
				}
			}
		}
	}
	@FXML
	public void SubmitOnAction(ActionEvent e) {
		ta.setText(ta.getText()+" "+combo.getSelectionModel().getSelectedItem().toString());
		combo.getItems().clear();
	}
	public static void trigram(ArrayList<String> data,HashMap<String, Words> trigram) {
		
        for (int i = 0; i < data.size(); i++) {

            String[] lines = data.get(i).split(" ");
            for (int j = 0; j < lines.length-2; j++) {

                String key = lines[j] + " " + lines[j + 1];
                TargetWord targetWord;
                Words word = new Words();
                if (!trigram.containsKey(key)) {

                    targetWord = new TargetWord(lines[j + 2], 1);
                    ArrayList<TargetWord> list = new ArrayList<>();
                    list.add(targetWord);
                    word.setCount(1);
                    word.setTargetWord(list);
                    word.setFirstTwoWords(key);
                    trigram.put(word.getFirstTwoWords(), word);

                } else  {

                    trigram.get(key).setCount(trigram.get(key).getCount() + 1);
                    int d = 0;
                    for (int k = 0; k < trigram.get(key).getTargetWord().size(); k++) {

                        if (trigram.get(key).getTargetWord().get(k).getStr().equals(lines[j + 2])) {
                            System.out.println(""+lines[j+2]+"");
                            trigram.get(key).getTargetWord().get(k).setCount(trigram.get(key).getTargetWord().get(k).getCount() + 1);
                            d = 1;
                        }

                    } if (d==0){
                        trigram.get(key).getTargetWord().add(new TargetWord(lines[j + 2], 1));
                    }

                }

            }

        }



       
    }
}
