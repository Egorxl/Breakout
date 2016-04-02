

import acm.program.GraphicsProgram;


public class BreakoutGame extends GraphicsProgram{
	private static final int BLOCK_SIZE_X = 25 ;
	private static final int BLOCK_SIZE_Y = 10;
	private static final int SPACE_BETWEEN_BLOCKS=2;
	private static final int PADDLE_SIZE_X = BLOCK_SIZE_X*2;
	private static final int PADDLE_SIZE_Y = BLOCK_SIZE_Y;
	private static final int COLLIDER_RADIUS = 6;
	
	public void run(){
		setBlocks();
		createPaddle();
		createCollider();
		Game();
	}
	private void setBlocks() {
		// TODO Auto-generated method stub
		
	}
	private void createPaddle() {
		// TODO Auto-generated method stub
		
	}
	private void createCollider() {
		// TODO Auto-generated method stub
		
	}
	private void Game() {
		//while
		// TODO create keyboard listeners
		colliderDirectionX();
		colliderDirectionY();
		blockCollision();
	}
	private void blockCollision() {
		// TODO check for collision between Collider and Block
		
	}
	private void colliderDirectionY() {
		// TODO create boolean to upward-downward direction
		
	}
	private void colliderDirectionX() {
		// TODO create booleans for right-left direction
		
	}

}
