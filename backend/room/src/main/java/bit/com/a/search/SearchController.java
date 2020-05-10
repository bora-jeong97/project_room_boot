package bit.com.a.search;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bit.com.a.dummyData.DongDto;
import bit.com.a.dummyData.GuDto;
import bit.com.a.dummyData.SiDto;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	
	@Autowired
	SearchService SearchService;

	//getDong, getGu, getSi, getMarker 4개가 필요함.

	@PostMapping("/getDong")
	public List<DongDto> getDong(Double swLat, Double swLng, Double neLat, Double neLng, String roomTypeOption, String roomPayOption,
	 int deposit , int lease , int monthRent , int roomSpace) {
		System.out.println("SearchController , getDong 접근 확인");
		/*
		System.out.println(roomTypeOption);
		System.out.println(roomPayOption);
		System.out.println(deposit);
		System.out.println(lease);
		System.out.println(monthRent);
		System.out.println(roomSpace);
		*/
        return SearchService.getDong(swLat, swLng, neLat, neLng, roomTypeOption, roomPayOption, deposit, lease, monthRent, roomSpace);
	}
	@PostMapping("/getGu")
	public List<GuDto> getGu(Double swLat, Double swLng, Double neLat, Double neLng, String roomTypeOption, String roomPayOption,
	int deposit , int lease , int monthRent , int roomSpace) {
		System.out.println("SearchController , getGu 접근 확인");

        return SearchService.getGu(swLat, swLng, neLat, neLng, roomTypeOption, roomPayOption, deposit, lease, monthRent, roomSpace);
	}
	@PostMapping("/getSi")
	public List<SiDto> getSi(Double swLat, Double swLng, Double neLat, Double neLng) {
		System.out.println("SearchController , getSi 접근 확인");

        return SearchService.getSi(swLat, swLng, neLat, neLng);
	}


	@PostMapping("/getJson")
	@ResponseBody
	public JSONObject getJson() {
		System.out.println("SearchController , getJson 접근 확인");

        String jsonFileName = "seoul_25per_last.json";
		String jsonPath = SearchController.class.getResource("").getPath() + "..\\dummyData\\list\\" + jsonFileName;
		System.out.println(jsonPath);

		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(jsonPath));
			JSONObject jo = (JSONObject) obj;

			return jo;
		}catch (FileNotFoundException e) {
            System.out.println("파일이 발견되지 않았습니다. (FileNotFoundException)");
            System.out.println("지정된 경로 및 파일명 : " + jsonPath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("파일을 읽는데 문제가 발생하였습니다. (IOException)");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("parse를 진행하는데 문제가 발생하였습니다. (ParseException)");
            e.printStackTrace();
        }
        return null;
	}

	@PostMapping("/getRoomList")
	@ResponseBody
	public List<SGDDto> getRoomList(String keyword) {
		System.out.println("SearchController , getRoomList 접근 확인");

		return SearchService.getRoomList(keyword);

		/*
        String jsonFileName = "seoul_25per_last.json";
		String jsonPath = SearchController.class.getResource("").getPath() + "..\\dummyData\\list\\" + jsonFileName;
		System.out.println(jsonPath);

		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(jsonPath));
			JSONObject jo = (JSONObject) obj;

			return jo;
		}catch (FileNotFoundException e) {
            System.out.println("파일이 발견되지 않았습니다. (FileNotFoundException)");
            System.out.println("지정된 경로 및 파일명 : " + jsonPath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("파일을 읽는데 문제가 발생하였습니다. (IOException)");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("parse를 진행하는데 문제가 발생하였습니다. (ParseException)");
            e.printStackTrace();
        }
		return null;
		*/
	}


	/*
	      request("post", "search/getRoomList", params)
        .then(res => {
          console.log(res);
          //리스트를 data안에 집어넣기
          //this.roomLists = res;
        })
		.catch(error => console.log(error));
	*/
}