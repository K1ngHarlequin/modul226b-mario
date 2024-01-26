import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World {
    private static final int SCROLL_SPEED = 5;
    private Mario mario;

    public MyWorld() {    
        super(600, 400, 1);
        setBackground("background.PNG"); // Set the background image
        prepare();
    }
    
    private void prepare() {
        mario = new Mario();
        addObject(mario, 300, getHeight() - 100); // Position Mario in the middle

        // Create a series of ground objects to form the ground
        for (int i = 0; i < getWidth() * 2; i += 64) { // Create ground across the world
            Ground ground = new Ground();
            addObject(ground, i, getHeight() - ground.getImage().getHeight() / 2);
        }
    }

    public void act() {
        if (Greenfoot.isKeyDown("left")) {
            scrollWorld(-SCROLL_SPEED);
        }
        if (Greenfoot.isKeyDown("right")) {
            scrollWorld(SCROLL_SPEED);
        }
    }

    private void scrollWorld(int speed) {
        // Move all non-Mario objects
        for (Object obj : getObjects(null)) {
            Actor actor = (Actor) obj;
            if (!(actor instanceof Mario)) {
                actor.setLocation(actor.getX() - speed, actor.getY());
            }
        }

        // Add logic to add/remove objects based on scroll position if needed
    }
}
