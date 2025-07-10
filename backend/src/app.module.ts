import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { InstantAccessModule } from './instant-access/instant-access.module';

@Module({
  imports: [InstantAccessModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
