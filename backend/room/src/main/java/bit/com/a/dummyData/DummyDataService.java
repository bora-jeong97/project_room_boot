package bit.com.a.dummyData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DummyDataService {

    @Autowired
    DummyDataDao dummyDataDao;
    
    public String createDummy() { 
        //초기 데이터 세팅
        System.out.println("DummyDataService , createDummy 접근 확인");
        System.out.println("텍스트 파일에 있는 지역 데이터를 전부 입력합니다.");
        
        String fileName = "1번지역_서울_build_seoul.txt";
        //String fileName = "";

        String jsonFileName = "seoul_25per_last.json";
        String path = DummyDataService.class.getResource("").getPath() + "list\\" + fileName;
        String jsonPath = DummyDataService.class.getResource("").getPath() + "list\\" + jsonFileName;

        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(jsonPath));
            JSONObject jsonObject = (JSONObject) obj;
            
            String line = "";
            while((line = bufferReader.readLine()) != null){
                //System.out.println(line); 
                
                String[] splitTemp = line.split("-");
                double latitude = 0.0; //위도
                double longitude = 0.0; //경도
                int count = 0;  //더한 갯수.

                //정해진 문서 양식에 맞는가? 제대로 되어있다면 3이 출력될 것임.
                if(splitTemp.length == 3) {
                    int id = 0;

                    JSONObject jsonTemp = (JSONObject)jsonObject.get(splitTemp[2]);
                    String strTemp = (String)jsonTemp.get("type");

                    //JSON 데이터를 불러와서 위도랑 경도를 중앙위치로 계산. 
                    if(strTemp.equals("single")) {
                        JSONArray jsonArray = (JSONArray)jsonTemp.get("path");

                        for(int i = 0;i < jsonArray.size(); i++) {
                            JSONArray jsonArray2 = (JSONArray)jsonArray.get(i);

                            latitude += (Double)jsonArray2.get(1);
                            longitude += (Double)jsonArray2.get(0);
                            count++;    
                        }
                    }else{  //multi
                        JSONArray jsonArray = (JSONArray)jsonTemp.get("path");

                        for(int i = 0;i < jsonArray.size(); i++) {
                            JSONArray jsonArray2 = (JSONArray)jsonArray.get(i);

                            for(int j = 0;j < jsonArray2.size(); j++) {
                                JSONArray jsonArray3 = (JSONArray)jsonArray2.get(j);

                                latitude += (Double)jsonArray3.get(1);
                                longitude += (Double)jsonArray3.get(0);
                                count++;    
                            }
                        }
                    }
                    registerSi(splitTemp[0]);

                    SiDto siDto = new SiDto();
                    siDto.setName(splitTemp[0]);
                    id = dummyDataDao.getSiId(siDto);
                    siDto = dummyDataDao.getSi(id);

                    registerGu(splitTemp[1], siDto);

                    GuDto guDto = new GuDto();
                    guDto.setName(splitTemp[1]);
                    guDto.setSiDto(siDto);
                    guDto.setSiId(guDto.getSiDto().getId());
                    id = dummyDataDao.getGuId(guDto);
                    guDto = dummyDataDao.getGu(id);

                    /*
                    System.out.println(latitude);
                    System.out.println(longitude);
                    System.out.println(count);
                    System.out.println(latitude/count);
                    System.out.println(longitude/count);
                    System.out.println("구분선");
                    */
                    registerDong(splitTemp[2], guDto, (latitude/count), (longitude/count));

                }else {
                    System.out.println("양식이 다른 라인입니다.");
                    System.out.println(line);
                }

            }
            bufferReader.close();
            System.out.println("완료");
            
        } catch (FileNotFoundException e) {
            System.out.println("파일이 발견되지 않았습니다. (FileNotFoundException)");
            System.out.println("지정된 경로 및 파일명 : " + path);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("파일을 읽는데 문제가 발생하였습니다. (IOException)");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("parse를 진행하는데 문제가 발생하였습니다. (ParseException)");
            e.printStackTrace();
        }
        return "ok?";
		//return dummyDataDao.registerData();
    }
    @Transactional
    private void registerSi(String splitTemp0) {
        SiDto siDto = new SiDto();
        siDto.setName(splitTemp0);
        siDto.setLatitude(-1.1);
        siDto.setLongitude(-1.1);
        int siTemp = dummyDataDao.checkSi(siDto);

        if(siTemp == 0) {   //같은 이름 없음.
            dummyDataDao.insertSi(siDto);
            //System.out.println("시 입력 실행");
        }else if(siTemp == 1){
            //System.out.println("시 중복 발견");
        }else {
            System.out.println("시 데이터에서 2개 이상의 같은 값 발견. 데이터 베이스 체크 요망." + splitTemp0);
        }

    }
    @Transactional
    private void registerGu(String splitTemp1, SiDto siDto) {
        GuDto guDto = new GuDto();
        guDto.setName(splitTemp1);
        guDto.setSiDto(siDto);
        guDto.setSiId(guDto.getSiDto().getId());
        guDto.setLatitude(-1.1);
        guDto.setLongitude(-1.1);
        int guTemp = dummyDataDao.checkGu(guDto);

        if(guTemp == 0) {   //같은 이름 없음.
            dummyDataDao.insertGu(guDto);
            //System.out.println("구 입력 실행");
        }else if(guTemp == 1){
            //System.out.println("구 중복 발견");
        }else {
            System.out.println("구 데이터에서 2개 이상의 같은 값 발견. 데이터 베이스 체크 요망." + splitTemp1);
        }
    }
    @Transactional
    private void registerDong(String splitTemp2, GuDto guDto, Double latitude, Double longitude) {
        DongDto dongDto = new DongDto();
        dongDto.setName(splitTemp2);
        dongDto.setGuDto(guDto);
        dongDto.setGuId(dongDto.getGuDto().getId());
        dongDto.setLatitude(latitude);
        dongDto.setLongitude(longitude);
        int dongTemp = dummyDataDao.checkDong(dongDto);

        if(dongTemp == 0) {   //같은 이름 없음.
            dummyDataDao.insertDong(dongDto);
            //System.out.println("동 입력 실행");
        }else if(dongTemp == 1){
            //System.out.println("동 중복 발견");
            System.out.println(splitTemp2);
            System.out.println(dongDto.toString());
        }else {
            System.out.println("동 데이터에서 2개 이상의 같은 값 발견. 데이터 베이스 체크 요망." + splitTemp2);
        }
    }
}