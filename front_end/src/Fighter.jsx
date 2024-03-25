import propTypes from 'prop-types';
import { AnimatedSprite } from '@pixi/react';
import HpBar from './HpBar';
import { useMemo } from 'react';
import * as PIXI from 'pixi.js';


const Fighter = ({ fighter, DEFAULT_HP, numJ }) => {

  const idleNb = 8;
  const attackNb = 6;
  const runNb = 8;
  const jumpNb = 2;
  const deathNb = 6;

  const idle = useMemo(() => {
    const textures = [];
    for (let i = 0; i < idleNb; i++) {
      const texture = PIXI.Texture.from(`idle/idle_${i}.png`);
      const time = 300/idleNb;
      textures.push({ texture, time });
    }
    return textures;
  }, []);
  
  const idleMirrored = useMemo(() => {
    const texturesMirrored = [];
    for (let i = 0; i < idleNb; i++) {
      const texture = PIXI.Texture.from(`idle/idle_${i}_mirrored.png`);
      const time = 300/idleNb;
      texturesMirrored.push({ texture, time });
    }
    return texturesMirrored;
  }, []);

  const attack = useMemo(() => {
    const textures = [];
    for (let i = 0; i < attackNb; i++) {
      const texture = PIXI.Texture.from(`attack/attack_${i}.png`);
      const time = 300/attackNb;
      textures.push({ texture, time });
    }
    return textures;
  }, []);

  const attackMirrored = useMemo(() => {
    const texturesMirrored = [];
    for (let i = 0; i < attackNb; i++) {
      const texture = PIXI.Texture.from(`attack/attack_${i}_mirrored.png`);
      const time = 300/attackNb;
      texturesMirrored.push({ texture, time });
    }
    return texturesMirrored;
  }, []);

  const run = useMemo(() => {
    const textures = [];
    for (let i = 0; i < runNb; i++) {
      const texture = PIXI.Texture.from(`run/run_${i}.png`);
      const time = 300/runNb;
      textures.push({ texture, time });
    }
    return textures;
  }, []);

  const runMirrored = useMemo(() => {
    const texturesMirrored = [];
    for (let i = 0; i < runNb; i++) {
      const texture = PIXI.Texture.from(`run/run_${i}_mirrored.png`);
      const time = 300/runNb;
      texturesMirrored.push({ texture, time });
    }
    return texturesMirrored;
  }, []);

  const jump = useMemo(() => {
    const textures = [];
    for (let i = 0; i < jumpNb; i++) {
      const texture = PIXI.Texture.from(`jump/jump_${i}.png`);
      const time = 300/jumpNb;
      textures.push({ texture, time });
    }
    return textures;
  }, []);

  const jumpMirrored = useMemo(() => {
    const texturesMirrored = [];
    for (let i = 0; i < jumpNb; i++) {
      const texture = PIXI.Texture.from(`jump/jump_${i}_mirrored.png`);
      const time = 300/jumpNb;
      texturesMirrored.push({ texture, time });
    }
    return texturesMirrored;
  }, []);

  const death = useMemo(() => {
    const textures = [];
    for (let i = 0; i < deathNb; i++) {
      const texture = PIXI.Texture.from(`death/death_${i}.png`);
      const time = 300/deathNb;
      textures.push({ texture, time });
    }
    return textures;
  }, []);

  const deathMirrored = useMemo(() => {
    const texturesMirrored = [];
    for (let i = 0; i < deathNb; i++) {
      const texture = PIXI.Texture.from(`death/death_${i}_mirrored.png`);
      const time = 300/deathNb;
      texturesMirrored.push({ texture, time });
    }
    return texturesMirrored;
  }, []);
  
  return (
    <>
      { fighter.direction === true && fighter.status === 'death' && <AnimatedSprite
        textures={death}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={false}
        x={300 + fighter.pos * 10}
        y={427}
      /> }

      { fighter.direction === true && fighter.status === 'idle' && <AnimatedSprite
        textures={idle}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={true}
        x={300 + fighter.pos * 10}
        y={427}
      /> }

      { fighter.direction === true && fighter.status === 'attack' && <AnimatedSprite
        textures={attack}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={false}
        x={300 + fighter.pos * 10}
        y={427}
      /> }

      { fighter.direction === true && fighter.status === 'run' && <AnimatedSprite
        textures={run}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={true}
        x={300 + fighter.pos * 10}
        y={427}
      /> }

      { fighter.direction === true && fighter.status === 'jump' && <AnimatedSprite
        textures={jump}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={false}
        x={300 + fighter.pos * 10}
        y={427}
      /> }

      { fighter.direction === false && fighter.status === 'death' && <AnimatedSprite
        textures={deathMirrored}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={false}
        x={300 + fighter.pos * 10}
        y={427}
      /> }
        
      { fighter.direction === false && fighter.status === 'idle' && <AnimatedSprite
        textures={idleMirrored}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={true}
        x={300 + fighter.pos * 10}
        y={427}
      /> }
        
      { fighter.direction === false && fighter.status === 'attack' && <AnimatedSprite
        textures={attackMirrored}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={false}
        x={300 + fighter.pos * 10}
        y={427}
      /> }
        
      { fighter.direction === false && fighter.status === 'run' && <AnimatedSprite
        textures={runMirrored}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={true}
        x={300 + fighter.pos * 10}
        y={427}
      /> }
        
      { fighter.direction === false && fighter.status === 'jump' && <AnimatedSprite
        textures={jumpMirrored}
        //animationSpeed={0.1}
        isPlaying={true}
        loop={false}
        x={300 + fighter.pos * 10}
        y={427}
      /> }
        
      <HpBar fighter={fighter} DEFAULT_HP={DEFAULT_HP} numJ={numJ} />
    </>
  );
}

Fighter.propTypes = {
  fighter: propTypes.object.isRequired,
  DEFAULT_HP: propTypes.number.isRequired,
  numJ: propTypes.number.isRequired
}

export default Fighter;