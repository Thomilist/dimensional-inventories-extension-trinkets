package net.thomilist.dimensionalinventories.extension.trinkets.gametest.util;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public final class TrinketsHelper
{
    public static ItemStack get( final LivingEntity entity, final String groupKey, final String slotKey )
    {
        return TrinketsHelper.get( entity, groupKey, slotKey, 0 );
    }

    public static ItemStack get( final LivingEntity entity,
                                 final String groupKey,
                                 final String slotKey,
                                 final int index )
    {
        return TrinketsHelper.get( TrinketsApi.getTrinketComponent( entity ).orElseThrow(), groupKey, slotKey, index );
    }

    public static ItemStack get( final TrinketComponent trinkets, final String groupKey, final String slotKey )
    {
        return TrinketsHelper.get( trinkets, groupKey, slotKey, 0 );
    }

    public static ItemStack get( final TrinketComponent trinkets,
                                 final String groupKey,
                                 final String slotKey,
                                 final int index )
    {
        return trinkets.getInventory().get( groupKey ).get( slotKey ).getStack( index );
    }

    public static void set( final LivingEntity entity,
                            final String groupKey,
                            final String slotKey,
                            final ItemStack itemStack )
    {
        TrinketsHelper.set( entity, groupKey, slotKey, itemStack, 0 );
    }

    public static void set( final LivingEntity entity,
                            final String groupKey,
                            final String slotKey,
                            final ItemStack itemStack,
                            final int index )
    {
        TrinketsHelper.set(
            TrinketsApi.getTrinketComponent( entity ).orElseThrow(),
            groupKey,
            slotKey,
            itemStack,
            index
        );
    }

    public static void set( final TrinketComponent trinkets,
                            final String groupKey,
                            final String slotKey,
                            final ItemStack itemStack )
    {
        TrinketsHelper.set( trinkets, groupKey, slotKey, itemStack, 0 );
    }

    public static void set( final TrinketComponent trinkets,
                            final String groupKey,
                            final String slotKey,
                            final ItemStack itemStack,
                            final int index )
    {
        trinkets.getInventory().get( groupKey ).get( slotKey ).setStack( index, itemStack );
    }
}
