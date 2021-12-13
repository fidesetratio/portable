package com.app.rests.extra.easyui;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.EasyUIApplication;
import com.app.model.EasyUiResponse;
import com.app.model.User;

@RestController
public class EasyUiTableRest {
	
	
	@RequestMapping(value = {"/extra/app/list"}, method=RequestMethod.POST)	
	public EasyUiResponse<EasyUIApplication> list(@RequestParam(value="page",defaultValue="1") Integer page,@RequestParam(value="rows",defaultValue="10") Integer rows,
					@RequestParam(value="selecttype",defaultValue="") String selecttype){
	
		System.out.println(page);
		System.out.println(rows);
		System.out.println(selecttype);
		
		
		ArrayList<EasyUIApplication> app = new ArrayList<EasyUIApplication>();
		
			if(selecttype.trim().toLowerCase().equals("table")) {
				app = typeOfTable();
			}
		
			return new EasyUiResponse<>(true, app.size(), app);
		}

	
	
	private ArrayList<EasyUIApplication> typeOfTable(){
		ArrayList<EasyUIApplication> app = new ArrayList<EasyUIApplication>();
		EasyUIApplication uii = new EasyUIApplication(1, "Simple Table 2", "Simple Table");
		app.add(uii);
		uii = new EasyUIApplication(2, "Simple Table 2", "Simple Table");
		app.add(uii);
		
		return app;
	}
}
