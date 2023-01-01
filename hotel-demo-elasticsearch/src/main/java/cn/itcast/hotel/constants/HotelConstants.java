package cn.itcast.hotel.constants;

public class HotelConstants {
    public static final String MAPPING_TEMPLATE = """
             {
               "mappings": {
                 "properties": {
                   "info":{
                     "type": "text",
                     "analyzer": "ik_smart"
                   },
                   "email":{
                     "type": "keyword",
                     "index": "false"
                   },
                   "name":{
                     "properties": {
                       "firstName": {
                         "type": "keyword"
                       },
                       "lastName": {
                         "type": "keyword"
                       }
                     }
                   }
                 }
               }
             }
            """;
}
