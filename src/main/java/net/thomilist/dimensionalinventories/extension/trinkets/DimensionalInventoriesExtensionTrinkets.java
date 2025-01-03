package net.thomilist.dimensionalinventories.extension.trinkets;

import net.thomilist.dimensionalinventories.extension.DimensionalInventoriesExtension;
import net.thomilist.dimensionalinventories.extension.trinkets.module.TrinketsModuleGroup;

public class DimensionalInventoriesExtensionTrinkets
    extends DimensionalInventoriesExtension
{
    private static final String MOD_ID = "dimensional-inventories-extension-trinkets";

    public DimensionalInventoriesExtensionTrinkets()
    {
        super( DimensionalInventoriesExtensionTrinkets.MOD_ID, new TrinketsModuleGroup() );
    }
}
