import Model.RangeStrategy.IRangeStrategy;
import Model.RangeStrategy.RangeStrategyW1;

public class Main {


    public static void main(String[] args) {

        IRangeStrategy iRangeStrategy = RangeStrategyW1.getInstance();
        System.out.println(iRangeStrategy.getClass());


    }

}
