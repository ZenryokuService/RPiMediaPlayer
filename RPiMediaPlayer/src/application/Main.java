package application;
	
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
	/** 最大ウィンドウサイズ(幅) */
	private double windowWidth;
	/** 最大ウィンドウサイズ(高さ) */
	private int windowHeight;

	@Override
	public void start(Stage primaryStage) {
		try {
			// レイアウト(土台)になるペインを作成
			BorderPane root = new BorderPane();

			// WebView(ブラウザ)の作成
			WebView browser = new WebView();
			WebEngine engine = browser.getEngine();
			String aaa = getClass().getResource("iframe.html").toExternalForm();
			System.out.println(aaa);
			engine.load(aaa);

			root.setCenter(browser);
			// 画面を表示する土台(シーン)を作成
			Scene scene = new Scene(root,this.windowWidth,this.windowHeight);
			// JavaFX用のCSSを読み込む
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(event -> Platform.exit());
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
		windowWidth = 0;
		windowHeight = 0;
		// プロパティファイルの読み込み
		prop = new Properties();
		loadProperties();
		// ウィンドウ情報の取得(縦横の幅)
		initWindowInfo();
		
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
		Path propFile = Paths.get("resources/application.properties");
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
	 * ロードしたプロパティファイルのキーを返却。
	 * @return keySet
	 */
	public Set<String> getKeySet() {
		return keySet;
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

	/**
	 * 起動しているデバイスの画面サイズを取得する
	 */
	private void initWindowInfo() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle size = env.getMaximumWindowBounds();
		windowHeight = size.height;
		windowWidth = size.width;
	}

	/**
	 * @return the windowWidth
	 */
	public double getWindowWidth() {
		return windowWidth;
	}

	/**
	 * @param windowWidth the windowWidth to set
	 */
	public void setWindowWidth(double windowWidth) {
		this.windowWidth = windowWidth;
	}

	/**
	 * @return the windowHeight
	 */
	public int getWindowHeight() {
		return windowHeight;
	}

	/**
	 * @param windowHeight the windowHeight to set
	 */
	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}
	
}
