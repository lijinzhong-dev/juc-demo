package season_two.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: lijinzhong
 * @Date: 2019/12/20
 * @Description: season_two.entities
 * @version: 1.0
 */
@NoArgsConstructor
@Data
public class Person {
    private Integer id;
    private String personName;
    Person(String personName){
        this.personName=personName;
    }
}
