<?php
    namespace tests\SFL2ApplicationBundle\Controller;
    
    use SFL2\ApplicationBundle\Controller\Utilisateur;
    use PHPUnit\Framework\TestCase;
    
    class UtilisateurTest extends \Monolog\TestCase
    {
        public function UtilisateurAdd()
        {
            $user = new Utilisateur();
            $result = $user->add();
            
            $this->$result;
        }
    }

