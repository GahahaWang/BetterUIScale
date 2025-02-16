package net.rosemods.betteruiscale;

import com.mojang.serialization.Codec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.MathHelper;

import java.util.Optional;
import java.util.function.IntSupplier;

@Environment(value = EnvType.CLIENT)
public record MaxSuppliableIntSliderCallbacks(
        int minInclusive,
        IntSupplier maxSupplier,
        int encodableMaxInclusive
) implements SimpleOption.IntSliderCallbacks {

    @Override
    public Optional<Integer> validate(Integer integer) {
        return Optional.of(MathHelper.clamp(integer, this.minInclusive(), this.maxInclusive()));
    }

    @Override
    public int maxInclusive() {
        return this.maxSupplier.getAsInt();
    }

    @Override
    public Codec<Integer> codec() {
        return Codecs.rangedInt(this.minInclusive, this.encodableMaxInclusive + 1);
    }
}