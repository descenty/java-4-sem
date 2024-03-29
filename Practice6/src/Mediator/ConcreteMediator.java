package Mediator;

public class ConcreteMediator extends Mediator {
    private Colleague colleague1;
    private Colleague colleague2;

    public void setColleague1(Colleague colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(Colleague colleague2) {
        this.colleague2 = colleague2;
    }

    @Override
    public void send(String message, Colleague sender) {
        if (sender == colleague1)
            colleague2.notify(message);
        else
            colleague1.notify(message);
    }
}
