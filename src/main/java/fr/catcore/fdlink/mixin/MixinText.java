package fr.catcore.fdlink.mixin;

import fr.catcore.fdlink.api.minecraft.CompatText;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({TranslatableText.class, LiteralText.class})
public class MixinText implements CompatText {

    @Override
    public String fabric_Discord_Link$getMessage() {
        return ((Text)(Object)this).getString();
    }
}