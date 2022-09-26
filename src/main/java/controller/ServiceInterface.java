
package controller;

import beans.vo.ServiceProviderVo;


public interface ServiceInterface {
      public String update(int id, String newname, String newdescription, String newcategory,
            String newphoneNumer, String newcity, String  newaddress , Double newvalue);
      
      public String delete(String id);
      
      public String create(int id, ServiceProviderVo serviceProvider,String name, String description, String category,
            String phoneNumer, String city, String  address , Double value);
}
