package userInterface;

public class AnimationThread extends Thread {
    private WelcomePanel welcomePanel;

    public AnimationThread(WelcomePanel welcomePanel){
        this.welcomePanel = welcomePanel;
    }

    public void run(){
        while(true){
            welcomePanel.getVegetable().move();
            welcomePanel.repaint();
            try {
                sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}