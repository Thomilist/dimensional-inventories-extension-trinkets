package net.thomilist.dimensionalinventories.extension.trinkets.module;

import net.thomilist.dimensionalinventories.module.ModuleGroup;

public final class TrinketsModuleGroup
    extends ModuleGroup
{
    private static final String GROUP_ID = "trinkets";

    public TrinketsModuleGroup()
    {
        super( TrinketsModuleGroup.GROUP_ID );

        this.register( TrinketsModule.class );
    }
}
