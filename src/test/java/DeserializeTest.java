import com.thdjson.*;
import com.thdjson.entity.JSONArray;
import com.thdjson.entity.JSONFormat;
import com.thdjson.entity.JSONObject;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Theodore on 2017/7/12.
 */
public class DeserializeTest {

    static final String json ="{\n" +
            "    \"RECORDS\": [\n" +
            "        {\n" +
            "            \"Id\": 1,\n" +
            "            \"countryname\": \"Angola\",\n" +
            "            \"countrycode\": \"AO\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Id\": 2,\n" +
            "            \"countryname\": \"Afghanistan\",\n" +
            "            \"countrycode\": \"AF\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Id\": 3,\n" +
            "            \"countryname\": \"Albania\",\n" +
            "            \"countrycode\": \"AL\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Id\": 4,\n" +
            "            \"countryname\": \"Algeria\",\n" +
            "            \"countrycode\": \"DZ\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Id\": 5,\n" +
            "            \"countryname\": \"Andorra\",\n" +
            "            \"countrycode\": \"AD\"\n" +
            "        }\n" +
            "    ]\n" +
            "}\n";

    @Test
    public void testOnlyPublic() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = parser.parseObject(json);
        JSONDeserializer deserializer = new JSONDeserializer(true, true);
        Information test = deserializer.deserializeToObject(jsonObject, Information.class);
        System.out.println(test);
        JSONSerializer serializer = new JSONSerializer(true, true);
        System.out.println(serializer.serializeObject(test));
    }

    @Test
    public void testNotOnlyPublic() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = parser.parseObject(json);
        JSONDeserializer deserializer = new JSONDeserializer(true, false);
        Information test = deserializer.deserializeToObject(jsonObject, Information.class);
        System.out.println(test);
        JSONSerializer serializer = new JSONSerializer(true, false);
        System.out.println(serializer.serializeObject(test));
    }

    @Test
    public void testSubClass() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = parser.parseObject(json);
        JSONDeserializer deserializer = new JSONDeserializer(true, true);
        Information test = deserializer.deserializeToObject(jsonObject, VipInfo.class);
        System.out.println(test);
        JSONSerializer serializer = new JSONSerializer(true, false);
        System.out.println(serializer.serializeObject(test));
    }

    @Test
    public void testList() throws Exception {
        LinkedList<User> list = new LinkedList<>();
        list.add(new User(1, "123", "a"));
        list.add(new User(2, "123", "b"));
        JSONSerializer serializer = new JSONSerializer(true, false);
        JSONArray array = serializer.serializeList(list);
        System.out.println(array);
        JSONDeserializer deserializer = new JSONDeserializer(true, false);
        List users = deserializer.deserializeToList(array, User.class);
        System.out.println(users.get(0).toString() + users.get(1).toString());
    }

    @Test
    public void testMap() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Id", (Integer)233);
        map.put("countrycode", "1");
        map.put("countryname", "AL");
        JSONSerializer serializer = new JSONSerializer(true, false);
        JSONObject object = serializer.serializeMap(map);
        System.out.println(object);
        JSONDeserializer deserializer = new JSONDeserializer(true, false);
        User user = deserializer.deserializeToObject(object, User.class);
        System.out.println(user);
    }

    public static class Information {
        public User[] records;

        @Override
        public String toString() {
            String str = "";
            for (User user : records) {
                str += user;
            }
            return str;
        }
    }

    public static class User {
        public int Id;
        public String countrycode;
        public String countryname;

        public User() {}

        public User(int id, String code, String name) {
            Id = id;
            countrycode = code;
            countryname = name;
        }

        @Override
        public String toString() {
            return "" + Id + " " + countrycode + " " + countryname + "\n" ;
        }
    }

    public static class VipInfo extends Information {

        public String vipID;

        public VipInfo() {}

        @Override
        public String toString() {
            String str = vipID + "\n";
            for (User user : records) {
                str += user;
            }
            return str;
        }
    }
}


