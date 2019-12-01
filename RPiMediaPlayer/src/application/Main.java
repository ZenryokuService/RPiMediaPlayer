package application;
	
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * ラズパイ上でJavaFXを起動、Youtubeなどのメディアプレーヤーアプリを作成。
 * 
 * @author takunoji
 * @see https://github.com/ZenryokuService/RPiMediaPlayer
 * 2019/12/01
 */
public class Main extends Application {
	/** プロパティファイル */
	private Properties prop;
	/** プロパティファイルのキー・セット */
	private Set<String> keySet;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	/** コンストラクタ */
	public Main() {
		prop = new Properties();
	}
	/*****************************************
	 * 必要な処理を行うメソッド群(JUniテストを行う)*
	 *****************************************/

	/**
	 * プロパティファイルを読み込む。
	 * 初期起動時に設定を読み込むための処理
	 */
	public void loadProperties() {
		// java.nio.PathでJavaFxのPathではない
		Path propFile = Paths.get("application.properties");
		try {
			// プロパティファイルの読み込み
			prop.load(Files.newInputStream(propFile));
			keySet = convertKeySet(prop.keySet());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("System Error: can not read application.properties");
		}
	}

	/**
	 * application.propertiesから値を取得する。
	 * 
	 * @param key プロパティキー
	 * @return プロパティの値
	 */
	public String getProperty(String key) {
		return prop.getProperty(key);
	}

	/**
	 * 取得したSet<Object>をSet<String>に変換する
	 * @param set Propertiesクラスから取得したSet
	 * @return 作成したSet<String>
	 */
	private Set<String> convertKeySet(Set<Object> set) {
		Set<String> newSet = new HashSet<String>();
		set.forEach(obj -> {
			newSet.add(obj.toString());
		});
		return newSet;
	}
}
