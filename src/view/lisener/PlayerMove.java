package view.lisener;

import organisms.Human;
import sun.awt.SunHints;
import world.World;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class PlayerMove implements KeyEventDispatcher {
        private Human human;
        private World world;
        public PlayerMove(Human human, World world){
                this.human = human;
                this.world = world;
        }

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
                if(e.getID() == KeyEvent.KEY_RELEASED){
                       if(world.isHex()){
                               switch(e.getKeyCode()){
                                       case KeyEvent.VK_Q:
                                               human.moveSpeed(-1,-1);
                                               break;
                                       case KeyEvent.VK_W:
                                               human.moveSpeed(1,-1);
                                               break;
                                       case KeyEvent.VK_A:
                                               human.moveSpeed(-1,0);
                                               break;
                                       case KeyEvent.VK_S:
                                               human.moveSpeed(1,0);
                                               break;
                                       case KeyEvent.VK_Z:
                                               human.moveSpeed(-1,1);
                                               break;
                                       case KeyEvent.VK_X:
                                               human.moveSpeed(1,1);
                                               break;
                                       case KeyEvent.VK_P:
                                               human.skill();
                                               break;
                                       case KeyEvent.VK_ENTER:
                                               world.takeTurn();
                                               world.updateWorld();
                                               human.setMoved(false);
                                               human.update(false);
                                               break;
                               }
                       }else{
                               switch(e.getKeyCode()){
                                       case KeyEvent.VK_UP:
                                               human.moveSpeed(0,-1);
                                               break;
                                       case KeyEvent.VK_LEFT:
                                               human.moveSpeed(-1,0);
                                               break;
                                       case KeyEvent.VK_RIGHT:
                                               human.moveSpeed(1,0);
                                               break;
                                       case KeyEvent.VK_DOWN:
                                               human.moveSpeed(0,1);
                                               break;
                                       case KeyEvent.VK_P:
                                               human.skill();
                                               break;
                                       case KeyEvent.VK_ENTER:
                                               world.takeTurn();
                                               world.updateWorld();
                                               human.setMoved(false);
                                               human.update(false);
                                               break;
                               }
                       }
                        if(e.getKeyCode() != KeyEvent.VK_ENTER){
                                human.setMoved(true);
                                human.update(true);
                        }
                }
                return false;
        }
}
