package com.mygdx.game.Quests;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Entitys.Chest;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.ResourceManager;

import static com.mygdx.utils.Constants.TILE_SIZE;

public class LocateQuest extends Quest {
    private final Vector2 loc;
    private float radius;

    public LocateQuest() {
        super();
        name = "Find a chest";
        description = "North east";
        reward = 100;
        loc = new Vector2();
        radius = -1;
    }

    public LocateQuest(Vector2 pos, float r) {
        this();
        Chest chest = new Chest();
        chest.setPosition(pos);
        loc.set(pos);
        radius = r * r;
        pos.scl(1/TILE_SIZE).sub(50, 50); // centres on 0, 0
        description = "";
        if(pos.y > 0) {
            description += "North ";
        }
        else if(pos.y < 0) {
            description += "South ";
        }
        if(pos.x > 0) {
            description += "West";
        }
        else if(pos.x < 0) {
            description += "East";
        }

        }

    @Override
    public boolean checkCompleted(Player p) {
        if (radius == -1) {
            return false;
        }
        Vector2 delta = p.getPosition().cpy();
        delta.sub(loc);
        float l = delta.len2();
        isCompleted = l < radius;
        return isCompleted;
    }

}
