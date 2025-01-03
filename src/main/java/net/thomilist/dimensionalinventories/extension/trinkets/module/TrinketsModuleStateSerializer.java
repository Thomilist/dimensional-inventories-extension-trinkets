package net.thomilist.dimensionalinventories.extension.trinkets.module;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import net.minecraft.nbt.NbtCompound;
import net.thomilist.dimensionalinventories.util.gson.SerializerPair;

import java.lang.reflect.Type;

public class TrinketsModuleStateSerializer
    implements SerializerPair<TrinketsModuleState>
{
    @Override
    public TrinketsModuleState fromJson( final JsonElement json,
                                         final Type type,
                                         final JsonDeserializationContext context )
        throws JsonParseException
    {
        final TrinketsModuleState trinketsModuleState = new TrinketsModuleState();
        trinketsModuleState.trinketComponentNbt = context.deserialize( json, NbtCompound.class );
        return trinketsModuleState;
    }

    @Override
    public JsonElement toJson( final TrinketsModuleState src, final Type type, final JsonSerializationContext context )
    {
        return context.serialize( src.trinketComponentNbt, NbtCompound.class );
    }
}
