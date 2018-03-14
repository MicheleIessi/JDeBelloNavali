package Model.Factories;

import Model.RangeStrategy.IRangeStrategy;

public class RangeStrategyFactory {

    private static RangeStrategyFactory instance = null;

    public IRangeStrategy createStrategy(String strategyName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        String path = "Model.RangeStrategy.RangeStrategy";
        return (IRangeStrategy) Class.forName(path+strategyName).newInstance();

    }

    public static RangeStrategyFactory getInstance() {
        if(instance == null) {
            instance = new RangeStrategyFactory();
        }
        return instance;
    }

}
