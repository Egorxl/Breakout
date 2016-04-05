
import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class BreakoutGame extends GraphicsProgram {
	private static final int SPACE_BETWEEN_BLOCKS = 4;
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HIGHT = 600;

	private static final int NUMBER_OF_BLOCKS = 10;
	private static final int BLOCK_WIDTH = (WINDOW_WIDTH - (NUMBER_OF_BLOCKS - 1)
			* SPACE_BETWEEN_BLOCKS)/ NUMBER_OF_BLOCKS;
	private static final int BLOCK_HIGHT = 8;
	private static final int NUMBER_OF_LINES = 10;
	private static final int BLOCKS_TOP_OFFSET = 70;

	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HIGHT = 10;
	private static final int PADDLE_BOTTOM_OFFSET = 30;

	private static final int COLLIDER_RADIUS = 10;

	private int blockX = 0;
	private int blockY = BLOCKS_TOP_OFFSET;

	public void run() {
		this.setSize(WINDOW_WIDTH, WINDOW_HIGHT);
		 setBlocks();
		// createPaddle();
		// createCollider();
		// Game();
	}

	private void setBlocks() {
		for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
			for(int f=0;f<NUMBER_OF_LINES/5;f++){
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.RED);
				block.setFilled(true);
				block.setFillColor(Color.RED);
				add(block, blockX, blockY);
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for(int f=0;f<NUMBER_OF_LINES/5;f++){
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.ORANGE);
				block.setFilled(true);
				block.setFillColor(Color.ORANGE);
				add(block, blockX, blockY);
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for(int f=0;f<NUMBER_OF_LINES/5;f++){
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.YELLOW);
				block.setFilled(true);
				block.setFillColor(Color.YELLOW);
				add(block, blockX, blockY);
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for(int f=0;f<NUMBER_OF_LINES/5;f++){
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.GREEN);
				block.setFilled(true);
				block.setFillColor(Color.GREEN);
				add(block, blockX, blockY);
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for(int f=0;f<NUMBER_OF_LINES/5;f++){
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.CYAN);
				block.setFilled(true);
				block.setFillColor(Color.CYAN);
				add(block, blockX, blockY);
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			blockY= BLOCKS_TOP_OFFSET;
			blockX+=BLOCK_WIDTH+SPACE_BETWEEN_BLOCKS;
		}
	}

	private void createPaddle() {
		// TODO Auto-generated method stub

	}

	private void createCollider() {
		// TODO Auto-generated method stub

	}

	private void Game() {
		// while
		// TODO create keyboard listeners
		colliderDirectionX();
		colliderDirectionY();
		blockCollision();
	}

	private void blockCollision() {
		// TODO EGOR check for collision between Collider and Block

	}

	private void colliderDirectionY() {
		// TODO create boolean to upward-downward direction

	}

	private void colliderDirectionX() {
		// TODO create booleans for right-left direction

	}

}
