import java.awt.Color;
import java.awt.event.*;

import acm.graphics.*;
import acm.program.*;

public class BreakoutGame extends GraphicsProgram {
	private static final int SPACE_BETWEEN_BLOCKS = 4;
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HIGHT = 600;

	private static final int NUMBER_OF_BLOCKS = 10;
	private static final int BLOCK_WIDTH = (WINDOW_WIDTH - (NUMBER_OF_BLOCKS - 1)
			* SPACE_BETWEEN_BLOCKS)
			/ NUMBER_OF_BLOCKS;
	private static final int BLOCK_HIGHT = 8;
	private static final int NUMBER_OF_LINES = 10;
	private static final int BLOCKS_TOP_OFFSET = 70;

	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HIGHT = 10;
	private static final int PADDLE_BOTTOM_OFFSET = 30;

	private static final int COLLIDER_RADIUS = 10;

	private static final int SPEED = 1;

	private int blockX = 0;
	private int blockY = BLOCKS_TOP_OFFSET;

	int numberOfBlocks = 0;

	GLabel info = new GLabel(
			"чтобы начать игру, нажмите пробел \n чтобы запустить шар, нажмите левый SHIFT");

	int paddleX = (WINDOW_WIDTH - PADDLE_WIDTH) / 2;
	int paddleY = WINDOW_HIGHT - PADDLE_BOTTOM_OFFSET;
	GRect paddle = new GRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HIGHT);

	boolean field = false;
	boolean game = false;

	public void run() {
		addKeyListeners();
		this.setSize(WINDOW_WIDTH, WINDOW_HIGHT);
		addInstruction();
		;
	}

	private void createField() {
		remove(info);
		setBlocks();
		createCollider();
		createPaddle();
		System.out.println(numberOfBlocks);

	}

	private void addInstruction() {
		add(info, (WINDOW_WIDTH - info.getDescent()) / 4,
				(WINDOW_HIGHT - info.getAscent()) / 2);
	}

	private void setBlocks() {
		for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
			for (int f = 0; f < NUMBER_OF_LINES / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.RED);
				block.setFilled(true);
				block.setFillColor(Color.RED);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < NUMBER_OF_LINES / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.ORANGE);
				block.setFilled(true);
				block.setFillColor(Color.ORANGE);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < NUMBER_OF_LINES / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.YELLOW);
				block.setFilled(true);
				block.setFillColor(Color.YELLOW);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < NUMBER_OF_LINES / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.GREEN);
				block.setFilled(true);
				block.setFillColor(Color.GREEN);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < NUMBER_OF_LINES / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.CYAN);
				block.setFilled(true);
				block.setFillColor(Color.CYAN);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			blockY = BLOCKS_TOP_OFFSET;
			blockX += BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS;
		}
	}

	private void createPaddle() {
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle);
	}

	private void createCollider() {
		int colliderX = (WINDOW_WIDTH - PADDLE_WIDTH) / 2 + PADDLE_WIDTH / 2
				- COLLIDER_RADIUS;
		int colliderY = WINDOW_HIGHT - PADDLE_BOTTOM_OFFSET - COLLIDER_RADIUS
				* 2;
		GOval collider = new GOval(COLLIDER_RADIUS * 2, COLLIDER_RADIUS * 2);
		collider.setFilled(true);
		collider.setFillColor(Color.BLACK);
		add(collider, colliderX, colliderY);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 32 && !field) {
			createField();
			field = true;
		}
		if (e.getKeyCode() == 16){
			startGame();
			game = true;
		}
			

	}

	private void startGame() {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (game && e.getKeyCode() == 37 && paddle.getX() > 0) {
			paddle.move(-4, 0);
			pause(SPEED);
		}
		if (game && e.getKeyCode() == 39
				&& paddle.getX() + PADDLE_WIDTH < WINDOW_WIDTH) {
			paddle.move(4, 0);
			pause(SPEED);
		}

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
