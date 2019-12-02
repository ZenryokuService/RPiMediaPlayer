package application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Mainクラスのテストを行うクラス
 * @author takunoji
 * 2019/12/01
 */
public class MainTest {
	/** テスト対象クラス */
	private Main target;

	/**
	 * テストを行うための準備処理
	 */
	@Before
	public void initTest() {
		// テスト対象クラスのインスタンスを生成
		target = new Main();
	}

	/**************************************************
	 * 「testテストするメソッド名」でテストケース名を作成する  
	 * 複数テストするときは「_XXX」のように名前をつける
	 **************************************************/
	@SuppressWarnings("unchecked")
	/** convertKeySet()のテスト */
	@Test
	public void testConvertKeySet() {
		System.out.println("*** testConvertKeySet ***");
		Set<Object> set = new HashSet<>();
		set.add("test1");
		set.add("test2");
		set.add("test3");
		set.add("test4");
		Set<String> result = null;
		try {
			Method mes = target.getClass().getDeclaredMethod("convertKeySet", Set.class);
			mes.setAccessible(true);
			result = (Set<String>) mes.invoke(target, set);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			fail();
		} catch (SecurityException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			fail();
		}
		// resultのチェック
		result.forEach(System.out::println);
	}

	/** loadProperties()のテスト */
	@Test
	public void testLoadProperties() {
		System.out.println("*** testLoadProperties ***");
		target.loadProperties();
		Set<String> keySet = target.getKeySet();
		assertNotNull(keySet);
		keySet.forEach(System.out::println);
	}

	/** 画面サイズの取得テスト */
	@Test
	public void testInitWindowInfo() {
		// privateメソッドの呼び出し
		try {
			// メソッドの取得
			Method mes = target.getClass().getDeclaredMethod("initWindowInfo");
			// メソッドのアクセス範囲をpublicに変更
			mes.setAccessible(true);
			// メソッドの実行(引数も返り値もない
			mes.invoke(target);
			
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// テストの起動確認
		assertFalse(target.getWindowHeight() == 0);
		assertFalse(target.getWindowWidth() == 0);
	}
}
