package jp.co.internous.framepj.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.framepj.model.domain.MstCategory;
import jp.co.internous.framepj.model.domain.MstProduct;
import jp.co.internous.framepj.model.form.SearchForm;
import jp.co.internous.framepj.model.mapper.MstCategoryMapper;
import jp.co.internous.framepj.model.mapper.MstProductMapper;
import jp.co.internous.framepj.model.session.LoginSession;

/**
 * 商品検索に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/frameweb")
public class IndexController {
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private MstCategoryMapper categoryMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	
	/**
	 * トップページを初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		if (!loginSession.isLoggedIn() && loginSession.getTmpUserId() == 0) {
	        int tmpUserId = -(100000000 + new Random().nextInt(900000000));
	        loginSession.setTmpUserId(tmpUserId);
	    }
		List<MstProduct> products = productMapper.find();
		m.addAttribute("products", products);
		
		List<MstCategory> categories = categoryMapper.find();
		m.addAttribute("categories", categories);
		m.addAttribute("loginSession", loginSession);
		
		return "index";
	}
	
	/**
	 * 検索処理を行う
	 * @param f 検索用フォーム
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/searchItem")
	public String searchItem(SearchForm f, Model m) {
		String keywordsStr = f.getkeywords();
	    String normalized;
	    String[] keywords;

	    if (keywordsStr == null) {
	        normalized = "";
	        keywords = new String[]{""};
	    } else {
	        normalized = keywordsStr
	            .replaceAll("[\\t\\n\\r]+", " ")
	            .replace('\u3000', ' ')
	            .replaceAll(" {2,}", " ")
	            .trim();
	        keywords = normalized.split(" +");
	    }

	    int category = f.getCategory();

	    List<MstProduct> products = productMapper.findByCategoryAndProductName(category, keywords);

	    m.addAttribute("products", products);
	    m.addAttribute("categories", categoryMapper.find());
	    m.addAttribute("selected", category);
	    m.addAttribute("keywords", normalized);
	    m.addAttribute("loginSession", loginSession);

	    return "index";
	}
}
