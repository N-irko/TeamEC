package jp.co.internous.framepj.controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jp.co.internous.framepj.model.domain.TblCart;
import jp.co.internous.framepj.model.domain.dto.CartDto;
import jp.co.internous.framepj.model.form.CartForm;
import jp.co.internous.framepj.model.mapper.TblCartMapper;
import jp.co.internous.framepj.model.session.LoginSession;


/**
 * カート情報に関する処理のコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/frameweb/cart")
public class CartController {
	
	@Autowired
	private TblCartMapper cartMapper;

	@Autowired
	private LoginSession loginSession;
	
	// JSON形式の文字列とJavaオブジェクトを相互に変換するためのGsonオブジェクトを生成する
	// JavaScriptから届いたJSONは、そのままではJavaのListとして扱えない。Gsonは、そのJSONをJavaのMapやListへ翻訳する
	private Gson gson = new Gson();
	

	/**
	 * カート画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/")
	public String index(Model m) {
		int userId = loginSession.isLoggedIn() ? loginSession.getUserId() : loginSession.getTmpUserId();
	    List<CartDto> carts = cartMapper.findByUserId(userId);
	    m.addAttribute("carts", carts);
	    m.addAttribute("loginSession", loginSession);
	    return "cart";
	}

	/**
	 * カートに追加処理を行う
	 * @param f カート情報のForm
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {

		int userId = loginSession.isLoggedIn() ? loginSession.getUserId() : loginSession.getTmpUserId();
	    int productId = f.getProductId();
	    int productCount = f.getProductCount();

	    if (productCount <= 0) {
	        List<CartDto> carts = cartMapper.findByUserId(userId);
	        m.addAttribute("carts", carts);
	        m.addAttribute("loginSession", loginSession);
	        return "cart";
	    }

	    int count = cartMapper.findCountByUserIdAndProductId(userId, productId);

	    if (count == 0) {
	        TblCart cart = new TblCart();
	        cart.setUserId(userId);
	        cart.setProductId(productId);
	        cart.setProductCount(productCount);
	        cartMapper.insert(cart);
	    } else {
	        TblCart cart = new TblCart();
	        cart.setUserId(userId);
	        cart.setProductId(productId);
	        cart.setProductCount(productCount);
	        cartMapper.update(cart);
	    }

	    List<CartDto> carts = cartMapper.findByUserId(userId);
	    m.addAttribute("carts", carts);
	    m.addAttribute("loginSession", loginSession);
	    return "cart";
	}

	/**
	 * カート情報を削除する
	 * @param checkedIdList 選択したカート情報のIDリスト
	 * @return true:削除成功、false:削除失敗
	 */
	@PostMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		Type type = new TypeToken<Map<String, List<Integer>>>(){}.getType();
	    Map<String, List<Integer>> map = gson.fromJson(checkedIdList, type);
	    List<Integer> checkedIds = map.get("checkedIdList");

	    int deletedCount = cartMapper.deleteById(checkedIds);

	    return deletedCount > 0;
	}
}
