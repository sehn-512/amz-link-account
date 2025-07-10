import { Module } from '@nestjs/common';
import { InstantAccessService } from './instant-access.service';
import { InstantAccessController } from './instant-access.controller';

@Module({
  controllers: [InstantAccessController],
  providers: [InstantAccessService],
})
export class InstantAccessModule {}
