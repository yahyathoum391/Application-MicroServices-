import {Component, OnInit} from '@angular/core';
import {KeycloakServiceService} from './services/keycloak-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  constructor(public securityService:KeycloakServiceService) {
  }
  title = 'AngularKeycloak';

  ngOnInit(): void {
  }

  onLogout() {
    this.securityService.kc.logout();
  }

  onLogin() {
this.securityService.kc.login();
  }

  onChangePassword() {
    this.securityService.kc.accountManagement();
  }

  isAppManager() {
    return this.securityService.kc.hasRealmRole('app-manager');
  }
}
