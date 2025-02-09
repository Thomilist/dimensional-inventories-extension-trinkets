package net.thomilist.dimensionalinventories.extension.trinkets;

import net.thomilist.dimensionalinventories.extension.DimensionalInventoriesExtension;
import net.thomilist.dimensionalinventories.extension.trinkets.module.TrinketsModuleGroup;
import net.thomilist.dimensionalinventories.util.ModProperties;

public class DimensionalInventoriesExtensionTrinkets
    extends DimensionalInventoriesExtension
{
    public static final ModProperties PROPERTIES = new ModProperties( "dimensional-inventories-extension-trinkets" );
    private static final String MOD_ID = "dimensional-inventories-extension-trinkets";

    public DimensionalInventoriesExtensionTrinkets()
    {
        super( DimensionalInventoriesExtensionTrinkets.MOD_ID, new TrinketsModuleGroup() );
    }
}
