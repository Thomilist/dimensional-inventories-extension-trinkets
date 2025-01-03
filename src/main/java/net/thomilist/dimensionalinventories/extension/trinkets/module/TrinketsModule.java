package net.thomilist.dimensionalinventories.extension.trinkets.module;

import com.google.gson.Gson;
import net.minecraft.server.network.ServerPlayerEntity;
import net.thomilist.dimensionalinventories.module.base.JsonModule;
import net.thomilist.dimensionalinventories.module.base.ModuleBase;
import net.thomilist.dimensionalinventories.module.base.player.JsonPlayerModule;
import net.thomilist.dimensionalinventories.module.version.StorageVersion;

public class TrinketsModule
    extends ModuleBase
    implements JsonPlayerModule<TrinketsModuleState>
{
    private static final String MODULE_ID = "trinkets";
    private static final String DESCRIPTION = "Trinkets";

    private static final StorageVersion[] STORAGE_VERSIONS = {
        StorageVersion.V2
    };

    private static final Gson GSON = JsonModule.GSON_BUILDER
        .registerTypeAdapter( TrinketsModuleState.class, new TrinketsModuleStateSerializer() )
        .create();

    private final TrinketsModuleState state = new TrinketsModuleState();

    public TrinketsModule( final String groupId )
    {
        super( TrinketsModule.STORAGE_VERSIONS, groupId, TrinketsModule.MODULE_ID, TrinketsModule.DESCRIPTION );
    }

    @Override
    public Gson gson()
    {
        return TrinketsModule.GSON;
    }

    @Override
    public TrinketsModuleState newInstance( final ServerPlayerEntity serverPlayerEntity )
    {
        return new TrinketsModuleState( serverPlayerEntity );
    }

    @Override
    public TrinketsModuleState state()
    {
        return this.state;
    }

    @Override
    public TrinketsModuleState defaultState()
    {
        return new TrinketsModuleState();
    }
}
