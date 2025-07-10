import { Body, Controller, Post } from '@nestjs/common';
import { InstantAccessService } from './instant-access.service';

@Controller('instant-access')
export class InstantAccessController {
  constructor(private readonly iaService: InstantAccessService) {}

  @Post('sign')
  sign(@Body('payload') payload: string) {
    const privateKey = process.env.PRIVATE_KEY || '';
    return { signature: this.iaService.signPayload(payload, privateKey) };
  }

  @Post('verify')
  verify(@Body('payload') payload: string, @Body('signature') signature: string) {
    const publicKey = process.env.PUBLIC_KEY || '';
    const valid = this.iaService.verifyPayload(payload, signature, publicKey);
    return { valid };
  }

  @Post('get-user')
  getUser() {
    return this.iaService.getUserId();
  }
}
