package season_two.count_down_latch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 六国枚举
 */

public enum  CountryEnum {
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"韩"),
    FIVE(5,"赵"),
    SIX(6,"魏");

    @Getter private int num;
    @Getter private String countryName;

    CountryEnum(int num, String countryName) {
        this.num = num;
        this.countryName = countryName;
    }

    //遍历枚举
    public static CountryEnum forEach_CountryEnum(int index){
        //获取枚举CountryEnum中的所有值
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element : myArray) {
            if (index == element.getNum()){
                return element;
            }
        }
        return null;
    }
}
