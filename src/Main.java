public class Main {

    public static void main(String[] args){
        DependentRunnable one = new DependentRunnable("1", 1);
        DependentRunnable two = new DependentRunnable("2", 1);
        DependentRunnable three = new DependentRunnable("3", 1);
        DependentRunnable four = new DependentRunnable("4", 1);
        DependentRunnable five = new DependentRunnable("5", 2);
        DependentRunnable six = new DependentRunnable("6", 2);
        DependentRunnable seven = new DependentRunnable("7", 2);
        five.setDependency(one);
        five.setDependency(two);
        six.setDependency(three);
        six.setDependency(four);
        seven.setDependency(six);
        seven.setDependency(five);
        new Thread(one).start();
        new Thread(two).start();
        new Thread(three).start();
        new Thread(four).start();
        new Thread(five).start();
        new Thread(six).start();
        new Thread(seven).start();
    }
}
