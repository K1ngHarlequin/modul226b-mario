import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

public class Mario extends Actor {
    private int velocity = 0;
    private int acceleration = 1; // The rate of acceleration due to gravity
    private int jumpStrength = -12; // The strength of the jump
    private GreenfootImage standingImage = new GreenfootImage("mario2.png");
    private GreenfootImage[] runningImages = {
        new GreenfootImage("mario_run0.png"),
        new GreenfootImage("mario_run1.png"),
        new GreenfootImage("mario_run2.png"),
        new GreenfootImage("mario_run3.png")
    };
    private int currentImage = 0;
    private int animationCounter = 0;
    private boolean isMoving = false;
    private static final int ANIMATION_SPEED = 6;

    public Mario() {
        // Scale the images
        scaleImages();
        setImage(standingImage); // Set initial image
    }

    private void scaleImages() {
        // Scale standing image
        standingImage.scale(standingImage.getWidth() / 2, standingImage.getHeight() / 2);
        // Scale running images
        for (GreenfootImage image : runningImages) {
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
        }
    }

    public void act() {
        if (Greenfoot.isKeyDown("space") && isOnGround()) {
            jump();
        }
        checkFalling();
        updateImage(); // Update Mario's image
    }

    private void checkFalling() {
        if (!isOnGround()) {
            fall();
        } else if (velocity > 0) {
            velocity = 0; // Reset falling velocity when on ground
        }
    }

    private void fall() {
        setLocation(getX(), getY() + velocity);
        velocity += acceleration; // Apply gravity
        if (getY() >= getWorld().getHeight() - getImage().getHeight() / 2) {
            setLocation(getX(), getWorld().getHeight() - getImage().getHeight() / 2);
            velocity = 0;
        }
    }

    private boolean isOnGround() {
        Actor ground = getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, Ground.class);
        return ground != null;
    }

    private void jump() {
        velocity = jumpStrength;
    }

    private void updateImage() {
        // Check if Mario is moving (for animation purposes)
        isMoving = Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right");
        if (isMoving && isOnGround()) {
            animateRunning();
        } else {
            setImage(standingImage);
        }
    }

    private void animateRunning() {
        animationCounter++;
        if (animationCounter >= ANIMATION_SPEED) {
            animationCounter = 0;
            currentImage = (currentImage + 1) % runningImages.length;
            setImage(runningImages[currentImage]);
        }
    }
}
