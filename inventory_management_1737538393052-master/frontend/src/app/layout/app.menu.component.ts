import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import {RoleService} from "../zynerator/security/shared/service/Role.service";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {Router} from "@angular/router";
import {AppLayoutComponent} from "./app.layout.component";

@Component({
  selector: 'app-menu',
  templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {
  model: any[];
  modelanonymous: any[];
    modelAdmin: any[];
constructor(public layoutService: LayoutService, public app: AppComponent, public appMain: AppLayoutComponent, private roleService: RoleService, private authService: AuthService, private router: Router) { }
  ngOnInit() {
    this.modelAdmin =
      [

				{
                    label: '',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
                        {
                                    label: 'Dashboard',
                                    icon: 'pi pi-home',
                                    routerLink: ['/app/admin/input/dashboard/list']
                                },


					  {
						label: 'Sortie Management',
						icon: 'pi pi-arrow-up',
						items: [
								  {
									label: 'Liste sortie stock',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/output/sortie-stock/list']
								  },
						]
					  },
					  {
						label: 'Entree Management',
						icon: 'pi pi-download',
						items: [
								  {
									label: 'Liste entree stock',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/input/entree-stock/list']
								  },{
                                label: 'Liste valorisation stock',
                                icon: 'pi pi-fw pi-plus-circle',
                                routerLink: ['/app/admin/input/valorisation-stock/list']
                            },
						]
					  },
					  {
						label: 'Agent Management',
						icon: 'pi pi-users',
						items: [
								  {
									label: 'Liste fournisseur',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/agent/fournisseur/list']
								  },
						]
					  },
					  {
						label: 'Produit Management',
						icon: 'pi pi-tags',
						items: [
								  {
									label: 'Liste produit',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/catalogue/produit/list']
								  },
								  {
									label: 'Liste categorie produit',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/catalogue/categorie-produit/list']
								  },
						]
					  },
					  {
						label: 'Store Management',
						icon: 'pi pi-shopping-cart',
						items: [
								  {
									label: 'Liste stock',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/store/stock/list']
								  },
								  {
									label: 'Liste magasin',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/store/magasin/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-lock',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];

        if (this.authService.authenticated) {
            if (this.authService.authenticatedUser.roleUsers) {
              this.authService.authenticatedUser.roleUsers.forEach(role => {
                  const roleName: string = this.getRole(role.role.authority);
                  this.roleService._role.next(roleName.toUpperCase());
                  eval('this.model = this.model' + this.getRole(role.role.authority));
              })
            }
        }
  }

    getRole(text){
        const [role, ...rest] = text.split('_');
        return this.upperCaseFirstLetter(rest.join(''));
    }

    upperCaseFirstLetter(word: string) {
      if (!word) { return word; }
      return word[0].toUpperCase() + word.substr(1).toLowerCase();
    }

    onMenuClick(event) {
        this.appMain.onMenuClick(event);
    }
}
