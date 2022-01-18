
package server.maps;

import java.awt.Point;

import client.MapleClient;

public interface MapleMapObject {

    public int getObjectId();

    public void setObjectId(final int id);

    public MapleMapObjectType getType();

    public Point getPosition();

    public void setPosition(final Point position);

    public void sendSpawnData(final MapleClient client);

    //public void setPickedUp(final boolean pickedUp);
    public void sendDestroyData(final MapleClient client);
}
