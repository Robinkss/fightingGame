import { Graphics, Text } from '@pixi/react';
import propTypes from 'prop-types';

const HpBar = ({ fighter, DEFAULT_HP, numJ }) => {
  const barWidth = 50;
  const barHeight = 3;
  const barX = 375 + fighter.pos * 10;
  const barY = 477 + numJ * 100;
  const currentBarWidth = barWidth * (fighter.hp / DEFAULT_HP);

  return (
    <>
      <Graphics
        x={barX}
        y={barY}
        draw={g => {
          g.clear();
          g.beginFill(0xff0000);
          g.drawRect(0, 0, currentBarWidth, barHeight);
          g.endFill();

          g.lineStyle(2, 0x000000, 1);
          g.drawRect(0, 0, barWidth, barHeight);
        }}
      />
      <Text
        text={fighter.name}
        x={barX}
        y={barY - 20}
        style={{
          fontSize: 12,
          fill: '#ffffff'
        }}
      />
    </>
  );
};

HpBar.propTypes = {
  fighter: propTypes.object.isRequired,
  DEFAULT_HP: propTypes.number.isRequired,
  numJ: propTypes.number.isRequired
};

export default HpBar;