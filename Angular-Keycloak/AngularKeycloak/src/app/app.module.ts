import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, ApplicationRef, DoBootstrap, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import { SuppliersComponent } from './suppliers/suppliers.component';
import {KeycloakServiceService} from './services/keycloak-service.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RequestInteceptorService} from './services/request-inteceptor.service';

// export function KcFactory(kcSecurity:KeycloakServiceService) {
//   return ()=>kcSecurity.init();
// }

const secService=new KeycloakServiceService();
@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    SuppliersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {provide:KeycloakServiceService,useValue:secService},
    //{provide:APP_INITIALIZER,deps:[KeycloakServiceService],useFactory:KcFactory,multi:true},
    {provide:HTTP_INTERCEPTORS,useClass:RequestInteceptorService,multi:true}
  ],
  entryComponents:[AppComponent]
  //bootstrap: [AppComponent]
})
export class AppModule implements DoBootstrap{
  ngDoBootstrap(appRef: ApplicationRef): void {
   secService.init()
     .then(res=>{
       console.log(res);
       appRef.bootstrap(AppComponent)
     }).catch(err=>{
       console.log(err)
   });
  }
}
