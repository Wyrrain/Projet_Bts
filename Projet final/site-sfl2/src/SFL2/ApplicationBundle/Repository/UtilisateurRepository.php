<?php

namespace SFL2\ApplicationBundle\Repository;

/**
 * UtilisateurRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class UtilisateurRepository extends \Doctrine\ORM\EntityRepository
{
    public function getNombreUtilisateur()
    {
        $qb = $this->createQuery('COUNT *'
                . 'FROM utilisateur');
                   
                   
                
    
        return $qb
                
                ->getResult();
    }
}
