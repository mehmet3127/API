package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {
    
  public  Map<String,Object> reqresTestDataMethod(String name, Object job){

      Map<String,Object> expectedData =new HashMap<>();

      if (name!=null){
          expectedData.put("name",name);
      }

      if (job!=null){
          expectedData.put("job",job);
      }


      return expectedData;
  }
}
