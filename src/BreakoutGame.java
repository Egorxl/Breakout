import java.awt.Color;
import java.awt.datatransfer.SystemFlavorMap;
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

	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HIGHT = 10;
	private static final int PADDLE_BOTTOM_OFFSET = 30;

	private static final int COLLIDER_RADIUS = 10;

	private static final int SPEED = 2;

	private int blockX = 0;
	private int blockY = BLOCKS_TOP_OFFSET;

	int numberOfBlocks = 0;

	GLabel info = new GLabel("����� ������ ����, ������� ������");
	GLabel info2 = new GLabel("����� ��������� ��� ������� ������");

	int colliderX = (WINDOW_WIDTH - PADDLE_WIDTH) / 2 + PADDLE_WIDTH / 2
			- COLLIDER_RADIUS;
	int colliderY = WINDOW_HIGHT - PADDLE_BOTTOM_OFFSET - COLLIDER_RADIUS * 2;
	GOval collider = new GOval(COLLIDER_RADIUS * 2, COLLIDER_RADIUS * 2);

	int colliderDirectionY = (SPEED);
	int colliderDirectionX = (SPEED);

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
		while (!game) {
			pause(10);
		}
		if (game)
			startGame();
	}

	private void createField() {
		remove(info);
		add(info2, (WINDOW_WIDTH - info.getDescent()) / 4,
				(WINDOW_HIGHT - info.getAscent()) / 2 + info.getAscent());
		setBlocks();
		createCollider();
		createPaddle();
		System.out.println("���������� " + numberOfBlocks + " ������");

	}

	private void addInstruction() {
		add(info, (WINDOW_WIDTH - info.getDescent()) / 4,
				(WINDOW_HIGHT - info.getAscent()) / 2);
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
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < 2; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.ORANGE);
				block.setFilled(true);
				block.setFillColor(Color.ORANGE);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < 2; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.YELLOW);
				block.setFilled(true);
				block.setFillColor(Color.YELLOW);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < 2; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.GREEN);
				block.setFilled(true);
				block.setFillColor(Color.GREEN);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < 2; f++) {
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
		if (e.getKeyCode() == 32 && !field) {// ��������� ������� ������� �
												// ���������� ����
			createField();// ������� ����
			field = true;
		} else if (e.getKeyCode() == 32 && field && !game) {// ��������� �������
															// �������
			// � �������� ���
			game = true;
			colliderIsOnPaddle = false;
			remove(info2);
			// startGame();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {// ���������� ����������
		if (field && e.getKeyCode() == 37 && paddle.getX() > 0) {
			paddle.move(-4, 0);
			if (colliderIsOnPaddle == true)
				collider.move(-4, 0);
			pause(1);
		}
		if (field && e.getKeyCode() == 39
				&& paddle.getX() + PADDLE_WIDTH < WINDOW_WIDTH) {
			if (colliderIsOnPaddle == true)
				collider.move(4, 0);
			paddle.move(4, 0);
			pause(1);

		}

	}

	private void startGame() {// �������� ����
		int i = 0;
		while (game) {
			moveCollider(i);// ��������� ���
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
		checkForGameEnd();// ��������� ������������ � ������
		// �������(������)

	}

	private void checkForGameEnd() {
		if (collider.getY() - PADDLE_BOTTOM_OFFSET / 2 == WINDOW_HIGHT)
			game = false;

	}

	private void checkForBlockCollision() {
		if (getElementAt(collider.getX() + COLLIDER_RADIUS,
				collider.getY() - 0.1) != null
				&& getElementAt(collider.getX() + COLLIDER_RADIUS,
						collider.getY()) != collider) {// ��������� �������
														// ������� �����
			if (getElementAt(collider.getX() + COLLIDER_RADIUS, collider.getY()) != paddle) {
				GObject i = getElementAt(collider.getX() + COLLIDER_RADIUS,
						collider.getY() - 0.1);
				remove(i);
				numberOfBlocks--;
				System.out.println("�������� " + numberOfBlocks + " ������");
			}
			colliderDirectionY *= -1;
		}

	}

	private void checkForWallCollision() {
		if (collider.getY() < 0) {
			colliderDirectionY = -colliderDirectionY;

		}
		if (collider.getX() < 0
				|| collider.getX() + COLLIDER_RADIUS * 2 > WINDOW_WIDTH) {
			colliderDirectionX = -colliderDirectionX;
		}
	}

	private void moveCollider(int i) {// ����, �������� ���
		collider.move(-colliderDirectionX, -colliderDirectionY);
		pause(8);
	}

}
