import java.awt.Color;
import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class BreakoutGame extends GraphicsProgram {
	private static final int SPACE_BETWEEN_BLOCKS = 4;
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HIGHT = 600;

	private static final int NUMBER_OF_COLOMNES = 10;
	private static final int BLOCK_WIDTH = (WINDOW_WIDTH - (NUMBER_OF_COLOMNES - 1)
			* SPACE_BETWEEN_BLOCKS)
			/ NUMBER_OF_COLOMNES;
	private static final int BLOCK_HIGHT = 8;
	private static final int NUMBER_OF_RAWS = 10;
	private static final int BLOCKS_TOP_OFFSET = 70;

	GRect blocks[][] = new GRect[NUMBER_OF_COLOMNES][NUMBER_OF_RAWS];

	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HIGHT = 10;
	private static final int PADDLE_BOTTOM_OFFSET = 30;

	private static final int COLLIDER_RADIUS = 10;

	private static final int SPEED = 5;

	private int blockX = 0;
	private int blockY = BLOCKS_TOP_OFFSET;

	int numberOfBlocks = 0;

	GLabel info = new GLabel("����� ������ ����, ������� ������");
	GLabel info2 = new GLabel("����� ��������� ��� ������� ����� ����");

	int colliderX = (WINDOW_WIDTH - PADDLE_WIDTH) / 2 + PADDLE_WIDTH / 2
			- COLLIDER_RADIUS;
	int colliderY = WINDOW_HIGHT - PADDLE_BOTTOM_OFFSET - COLLIDER_RADIUS * 2;
	GOval collider = new GOval(COLLIDER_RADIUS * 2, COLLIDER_RADIUS * 2);

	private RandomGenerator rgen = RandomGenerator.getInstance();
	int randomNumberForCollderDirection = rgen.nextInt(25, 155);
	int colliderDirectionY = (randomNumberForCollderDirection);
	int colliderDirectionX = (180 - randomNumberForCollderDirection);

	int paddleX = (WINDOW_WIDTH - PADDLE_WIDTH) / 2;
	int paddleY = WINDOW_HIGHT - PADDLE_BOTTOM_OFFSET;
	GRect paddle = new GRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HIGHT);

	boolean field = false;
	boolean game = false;
	boolean colliderIsOnPaddle = true;

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
		System.out.println("�������� " + numberOfBlocks + " ������");

	}

	private void addInstruction() {
		add(info, (WINDOW_WIDTH - info.getDescent()) / 4,
				(WINDOW_HIGHT - info.getAscent()) / 2);
		add(info2, (WINDOW_WIDTH - info2.getDescent()) / 4,
				(WINDOW_HIGHT - info2.getAscent()) / 2 + info.getAscent());
	}

	private void setBlocks() {
		for (int i = 0; i < NUMBER_OF_COLOMNES; i++) {
			for (int f = 0; f < NUMBER_OF_RAWS / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.RED);
				block.setFilled(true);
				block.setFillColor(Color.RED);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < NUMBER_OF_RAWS / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.ORANGE);
				block.setFilled(true);
				block.setFillColor(Color.ORANGE);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < NUMBER_OF_RAWS / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.YELLOW);
				block.setFilled(true);
				block.setFillColor(Color.YELLOW);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < NUMBER_OF_RAWS / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.GREEN);
				block.setFilled(true);
				block.setFillColor(Color.GREEN);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < NUMBER_OF_RAWS / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.CYAN);
				block.setFilled(true);
				block.setFillColor(Color.CYAN);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			blockY = BLOCKS_TOP_OFFSET;
			blockX += BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS;
		}
	}

	private void createPaddle() {// ������� �����
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle);
	}

	private void createCollider() {// ������� ���
		collider.setFilled(true);
		collider.setFillColor(Color.BLACK);
		add(collider, colliderX, colliderY);
	}

	@Override
	public void keyTyped(KeyEvent e) {// ��������� ������� ������(�����������)

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("������ ������� ����� " + e.getKeyCode());// ���������,
																		// �����
																		// �������
																		// ������(���
																		// ��������
																		// ������
																		// �
																		// �����)
		if (e.getKeyCode() == 32 && !field) {// ��������� ������� �������
			createField();// ������� ����
			field = true;
		}
		if (e.getKeyCode() == 16 && field) {// ��������� ������� �������
			game = true;
			colliderIsOnPaddle = false;
			remove(info2);
			startGame();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {// ���������� ����������
		if (field && e.getKeyCode() == 37 && paddle.getX() > 0) {
			paddle.move(-SPEED, 0);
			if (colliderIsOnPaddle == true)
				collider.move(-SPEED, 0);
			pause(SPEED);
		}
		if (field && e.getKeyCode() == 39
				&& paddle.getX() + PADDLE_WIDTH < WINDOW_WIDTH) {
			if (colliderIsOnPaddle == true)
				collider.move(SPEED, 0);
			paddle.move(SPEED, 0);

		}

	}

	private void startGame() {// �������� ����
		while (game) {
			moveCollider();// ��������� ���
			checkForCollision();// ��������� ����
			checkForBlocksLeft();// ���������, �������� �� �����
		}
	}

	private void checkForBlocksLeft() {// ��������� �� ������� ������
		if (numberOfBlocks < 1)
			game = false;// ��������� ����

	}

	private void checkForCollision() {
		checkForWallCollision();// ��������� ������������ �� ������� ��
		checkForBlockCollision();
		//TODO checkForGameEnd();//��������� ������������ � ������ �������(������)

	}

	/*TODO*/private void checkForBlockCollision() {
//		if(collider.getX());
//			for (int i = 0; i < NUMBER_OF_COLOMNES; i++) {
//				for (int f = 0; f < NUMBER_OF_RAWS; f++) {
//					if (collider.equals(blocks[i][f])) {
//						remove(blocks[i][f]);//EXTERMINATE!!
//						numberOfBlocks--;
//						colliderDirectionX *= -1;
//						break;
//					}
//				}
//			}
		
	}

	private void checkForWallCollision() {
		if (collider.getY() <  0) {
//			for (int i = 0; i < NUMBER_OF_COLOMNES; i++) {
//				for (int f = 0; f < NUMBER_OF_RAWS; f++) {
//					if (collider.equals(blocks[i][f])) {
//						remove(blocks[i][f]);// EXTERMINATE!
//						numberOfBlocks--;//
//						colliderDirectionY *= -1;// ������������� ���
//						break;
//					}
//				}
//			}
			colliderDirectionY *= -1;

		}
		if (collider.getX() < 0
				|| collider.getX() + COLLIDER_RADIUS * 2 > WINDOW_WIDTH) {
			colliderDirectionX *= -1;
		}
	}

	private void moveCollider() {// ����, �������� ���
		collider.move(-colliderDirectionX, -colliderDirectionY);
		pause(SPEED);

	}

}
