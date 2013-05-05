package com.reed.birdseye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;

public class Player {
	static boolean move = true;
	static boolean up = false, down = false, left = false, right = false;
	static int currentDirection = 0; // 0 is down, 1 is up, 2 is left, 3 is
										// right
	boolean moving = false;
	
	int playerSpeed = 2;
	static float x = Level.middleX;
	static float y = Level.middleY;
	static final int playerWidth = 32;
	static final int playerHeight = 48;

	// pick stuff
	// Skeleton pickSkel = new Skeleton(Assets.pickRightSkeletonData);
	// Bone root = pickSkel.getRootBone();

	void input() {
		if (move) {
			if (!House.inHouse) {

				if ((Gdx.input.isKeyPressed(Keys.W)) || Android.up) {
					up = true;
					currentDirection = 1;
				} else
					up = false;
				if ((Gdx.input.isKeyPressed(Keys.A)) || Android.left) {
					left = true;
					currentDirection = 2;
				} else
					left = false;
				if ((Gdx.input.isKeyPressed(Keys.S)) || Android.down) {
					currentDirection = 0;
					down = true;
				} else
					down = false;
				if ((Gdx.input.isKeyPressed(Keys.D)) || Android.right) {
					right = true;
					currentDirection = 3;
				} else
					right = false;
			} else {
				if ((Gdx.input.isKeyPressed(Keys.W))) {
					y += playerSpeed;
					up = true;
				} else
					up = false;
				if ((Gdx.input.isKeyPressed(Keys.A))) {
					x -= playerSpeed;
					left = true;
				} else
					left = false;
				if ((Gdx.input.isKeyPressed(Keys.S))) {
					y -= playerSpeed;
					down = true;
				} else
					down = false;
				if ((Gdx.input.isKeyPressed(Keys.D))) {
					x += playerSpeed;
					right = true;
				} else
					right = false;
			}
		}
	}

	void move() {
		if (CollisionDetection.collisionAtPlayerTop == true
				&& Gdx.input.isKeyPressed(Keys.W)) {

			playerSpeed = 0;

		} else {
			if (CollisionDetection.collisionAtPlayerBottom == true
					&& Gdx.input.isKeyPressed(Keys.S)) {

				playerSpeed = 0;

			} else {
				if (CollisionDetection.collisionAtPlayerLeft == true
						&& Gdx.input.isKeyPressed(Keys.A)) {

					playerSpeed = 0;

				} else {
					if (CollisionDetection.collisionAtPlayerRight == true
							&& Gdx.input.isKeyPressed(Keys.D)) {

						playerSpeed = 0;

					} else {

						playerSpeed = 2;

					}
				}
			}

			if (move) {
				if (up) {
					Level.levelY -= playerSpeed;
					Level.grassY -= playerSpeed;
				}
				if (down) {
					Level.levelY += playerSpeed;
					Level.grassY += playerSpeed;
				}
				if (left) {
					Level.levelX += playerSpeed;
					Level.grassX += playerSpeed;
				}
				if (right) {
					Level.levelX -= playerSpeed;
					Level.grassX -= playerSpeed;
				}
			}
		}
	}

	float timer = 0;
	
	void setSprites() {
		if(timer < 4)
			timer += .1f;
		else
			timer = 0;
		
		//walking right animation
		if (right) {
			if (timer < 1) {
				Assets.mainChar = Assets.rightChar_STILL;
			} else if (timer > 1 && timer < 2) {
				Assets.mainChar = Assets.rightChar_LEFT;
			} else if (timer > 2 && timer < 3) {
				Assets.mainChar = Assets.rightChar_STILL;
			} else if (timer > 3) {
				Assets.mainChar = Assets.rightChar_RIGHT;
			}
		}
		
		//walking left animation
		if (left) {
			if (timer < 1) {
				Assets.mainChar = Assets.leftChar_STILL;
			} else if (timer > 1 && timer < 2) {
				Assets.mainChar = Assets.leftChar_LEFT;
			} else if (timer > 2 && timer < 3) {
				Assets.mainChar = Assets.leftChar_STILL;
			} else if (timer > 3) {
				Assets.mainChar = Assets.leftChar_RIGHT;
			}
		}
		
		//walking up animation
		if (up) {
			if (timer < 1) {
				Assets.mainChar = Assets.upChar_STILL;
			} else if (timer > 1 && timer < 2) {
				Assets.mainChar = Assets.upChar_LEFT;
			} else if (timer > 2 && timer < 3) {
				Assets.mainChar = Assets.upChar_STILL;
			} else if (timer > 3) {
				Assets.mainChar = Assets.upChar_RIGHT;
			}
		}
		
		//walking down animation
		if (down) {
			if (timer < 1) {
				Assets.mainChar = Assets.downChar_STILL;
			} else if (timer > 1 && timer < 2) {
				Assets.mainChar = Assets.downChar_LEFT;
			} else if (timer > 2 && timer < 3) {
				Assets.mainChar = Assets.downChar_STILL;
			} else if (timer > 3) {
				Assets.mainChar = Assets.downChar_RIGHT;
			}
		}
	}

	Tools tools = new Tools();

	void draw(SpriteBatch batch, BitmapFont font) {
		// font.draw(batch, "X:  " + Level.levelX, 850, 1030);
		// font.draw(batch, "Y:  " + Level.levelY, 850, 1000);
		// if (currentDirection == 3 && Resource.mining)
		// pickSkel.draw(batch);
		tools.draw(batch);
		tools.update();
		tools.changeTool();
		tools.direction();
		if (move)
			batch.draw(Assets.mainChar, x, y);
	}

	float time;

	void update() {
		// root.setX(Level.middleX + 11);
		// root.setY(Level.middleY - 11);

		// time += Gdx.graphics.getDeltaTime() * 1.2;
		// Assets.pickRightAnim.apply(pickSkel, time, true);
		// pickSkel.updateWorldTransform();
	}
}