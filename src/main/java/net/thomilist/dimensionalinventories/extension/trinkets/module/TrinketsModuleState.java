package net.thomilist.dimensionalinventories.extension.trinkets.module;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.thomilist.dimensionalinventories.module.base.player.PlayerModuleState;

import java.lang.reflect.Type;

public class TrinketsModuleState
    implements PlayerModuleState
{
    public NbtCompound trinketComponentNbt = new NbtCompound();

    public TrinketsModuleState()
    { }

    public TrinketsModuleState( final ServerPlayerEntity serverPlayerEntity )
    {
        this.loadFromPlayer( serverPlayerEntity );
    }

    @Override
    public void applyToPlayer( final ServerPlayerEntity serverPlayerEntity )
    {
        TrinketsApi
            .getTrinketComponent( serverPlayerEntity )
            .ifPresent( trinketComponent -> trinketComponent.readFromNbt( this.trinketComponentNbt ) );
    }

    @Override
    public void loadFromPlayer( final ServerPlayerEntity serverPlayerEntity )
    {
        this.trinketComponentNbt = new NbtCompound();
        TrinketsApi
            .getTrinketComponent( serverPlayerEntity )
            .ifPresent( trinketComponent -> trinketComponent.writeToNbt( this.trinketComponentNbt ) );
    }

    @Override
    public Type type()
    {
        return TrinketsModuleState.class;
    }
}
