package bit.com.a.search;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.com.a.dummyData.DongDto;
import bit.com.a.dummyData.GuDto;
import bit.com.a.dummyData.SiDto;

@Service
public class SearchService {

    @Autowired
    SearchDao searchDao;

    //getDong, getGu, getSi, getMarker 4개가 필요함.
    
    public List<DongDto> getDong(Double swLat, Double swLng, Double neLat, Double neLng, String roomTypeOption, String roomPayOption,
    int deposit , int lease , int monthRent , int roomSpace) { 
        System.out.println("SearchService , getDong 접근 확인");

        roomTypeOption = "{ \"roomTypeOption\":" + roomTypeOption + " }";
        roomPayOption = "{ \"roomPayOption\":" + roomPayOption + " }";
        
        System.out.println(roomTypeOption);
        System.out.println(roomPayOption);

        String RoomOption1 = "";
        String RoomOption2 = "";
        String RoomOption3 = "";
        String PayOption1 = "";
        String PayOption2 = "";
        String PayOption3 = "";

        try {
            JSONParser parser1 = new JSONParser();
            JSONParser parser2 = new JSONParser();
            Object obj1 = parser1.parse(roomTypeOption);
            JSONObject jsonObject1 = (JSONObject) obj1;
            Object obj2 = parser2.parse(roomPayOption);
            JSONObject jsonObject2 = (JSONObject) obj2;

            JSONArray jsonArray1 = (JSONArray)jsonObject1.get("roomTypeOption");
            JSONArray jsonArray2 = (JSONArray)jsonObject2.get("roomPayOption");

            switch (jsonArray1.size()) {
                case 3:
                RoomOption3 = (String)jsonArray1.get(2);
                case 2:
                RoomOption2 = (String)jsonArray1.get(1);
                case 1:
                RoomOption1 = (String)jsonArray1.get(0);
            }
            switch (jsonArray2.size()) {
                case 3:
                PayOption3 = (String)jsonArray2.get(2);
                case 2:
                PayOption2 = (String)jsonArray2.get(1);
                case 1:
                PayOption1 = (String)jsonArray2.get(0);
            }
            /*
            System.out.println(jsonArray1);
            System.out.println(jsonArray1.size());
            System.out.println(jsonArray2);
            System.out.println(jsonArray2.size());
            */

        }catch (ParseException e) {
            System.out.println("parse를 진행하는데 문제가 발생하였습니다. (ParseException)");
            e.printStackTrace();
        }
        
        return searchDao.getDong(swLat, swLng, neLat, neLng, RoomOption1, RoomOption2, RoomOption3, PayOption1, PayOption2, PayOption3,
        deposit , lease , monthRent , roomSpace);
    }
    public List<GuDto> getGu(Double swLat, Double swLng, Double neLat, Double neLng, String roomTypeOption, String roomPayOption,
    int deposit , int lease , int monthRent , int roomSpace) {
        System.out.println("SearchService , getGu 접근 확인");

        roomTypeOption = "{ \"roomTypeOption\":" + roomTypeOption + " }";
        roomPayOption = "{ \"roomPayOption\":" + roomPayOption + " }";
        
        System.out.println(roomTypeOption);
        System.out.println(roomPayOption);

        String RoomOption1 = "";
        String RoomOption2 = "";
        String RoomOption3 = "";
        String PayOption1 = "";
        String PayOption2 = "";
        String PayOption3 = "";

        try {
            JSONParser parser1 = new JSONParser();
            JSONParser parser2 = new JSONParser();
            Object obj1 = parser1.parse(roomTypeOption);
            JSONObject jsonObject1 = (JSONObject) obj1;
            Object obj2 = parser2.parse(roomPayOption);
            JSONObject jsonObject2 = (JSONObject) obj2;

            JSONArray jsonArray1 = (JSONArray)jsonObject1.get("roomTypeOption");
            JSONArray jsonArray2 = (JSONArray)jsonObject2.get("roomPayOption");

            switch (jsonArray1.size()) {
                case 3:
                RoomOption3 = (String)jsonArray1.get(2);
                case 2:
                RoomOption2 = (String)jsonArray1.get(1);
                case 1:
                RoomOption1 = (String)jsonArray1.get(0);
            }
            switch (jsonArray2.size()) {
                case 3:
                PayOption3 = (String)jsonArray2.get(2);
                case 2:
                PayOption2 = (String)jsonArray2.get(1);
                case 1:
                PayOption1 = (String)jsonArray2.get(0);
            }
            /*
            System.out.println(jsonArray1);
            System.out.println(jsonArray1.size());
            System.out.println(jsonArray2);
            System.out.println(jsonArray2.size());
            */

        }catch (ParseException e) {
            System.out.println("parse를 진행하는데 문제가 발생하였습니다. (ParseException)");
            e.printStackTrace();
        }

        return searchDao.getGu(swLat, swLng, neLat, neLng, RoomOption1, RoomOption2, RoomOption3, PayOption1, PayOption2, PayOption3,
        deposit , lease , monthRent , roomSpace);
    }
    public List<SiDto> getSi(Double swLat, Double swLng, Double neLat, Double neLng) { 
        System.out.println("SearchService , getSi 접근 확인");

        return searchDao.getSi(swLat, swLng, neLat, neLng);
    }
    public List<SGDDto> getRoomList(String keyword) { 
        System.out.println("SearchService , getRoomList 접근 확인");

        return searchDao.getRoomList(keyword);
    }
    
}