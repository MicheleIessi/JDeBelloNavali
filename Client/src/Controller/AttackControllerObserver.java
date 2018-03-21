package Controller;

public class AttackControllerObserver implements IClientObserver {

    @Override
    public void update(IClientController controller) {
        if("Attack".equalsIgnoreCase(controller.getFunction())) {
            System.out.println("Attacco ricevuto");
        }
    }
}
