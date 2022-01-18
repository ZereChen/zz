/*
�����̳�ETC.wz
*/

package handling.login;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import provider.MapleData;
import provider.MapleDataProviderFactory;
import provider.MapleDataTool;

public class LoginInformationProvider {

    private final static LoginInformationProvider instance = new LoginInformationProvider();
    protected final List<String> ForbiddenName = new ArrayList<String>();

    public static LoginInformationProvider getInstance() {
        return instance;
    }

    protected LoginInformationProvider() {
     //   System.out.println("");//�� �� �� �� �� �� �� : : :
        final String WZpath = System.getProperty("net.sf.odinms.wzpath");
        final MapleData nameData = MapleDataProviderFactory.getDataProvider(new File(WZpath + "/Etc.wz")).getData("ForbiddenName.img");
        for (final MapleData data : nameData.getChildren()) {
            ForbiddenName.add(MapleDataTool.getString(data));
        }
    }

    public final boolean isForbiddenName(final String in) {
        for (final String name : ForbiddenName) {
            if (in.contains(name)) {
                return true;
            }
        }
        return false;
    }
}
