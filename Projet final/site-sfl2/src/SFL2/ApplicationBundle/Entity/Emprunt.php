<?php

namespace SFL2\ApplicationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Emprunt
 *
 * @ORM\Table(name="emprunt")
 * @ORM\Entity(repositoryClass="SFL2\ApplicationBundle\Repository\EmpruntRepository")
 */
class Emprunt
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;



    /**
     * @var \DateTime
     *
     * @ORM\Column(name="debut", type="date", nullable=true)
     */
    private $debut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="fin", type="date", nullable=true)
     */
    private $fin;
    
    /**
     * @ORM\ManyToOne(targetEntity="SFL2\ApplicationBundle\Entity\Utilisateur")
     * @ORM\JoinColumn(nullable=false)
     */
    private $utilisateur;
    
    /**
     * @ORM\ManyToOne(targetEntity="SFL2\ApplicationBundle\Entity\Article")
     * @ORM\JoinColumn(nullable=false)
     */
    private $article;
    
    
    
    
  
    

    /**
     * Get id
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }


    /**
     * Set debut
     *
     * @param \DateTime $debut
     *
     * @return Emprunt
     */
    public function setDebut($debut)
    {
        $this->debut = $debut;

        return $this;
    }

    /**
     * Get debut
     *
     * @return \DateTime
     */
    public function getDebut()
    {
        return $this->debut;
    }

    /**
     * Set fin
     *
     * @param \DateTime $fin
     *
     * @return Emprunt
     */
    public function setFin($fin)
    {
        $this->fin = $fin;

        return $this;
    }

    /**
     * Get fin
     *
     * @return \DateTime
     * 
     */
    public function getFin()
    {
        return $this->fin;
    }

    /**
     * Set utilisateur
     *
     * @param \SFL2\ApplicationBundle\Entity\Utilisateur $utilisateur
     *
     * @return Emprunt
     */
    public function setUtilisateur(\SFL2\ApplicationBundle\Entity\Utilisateur $utilisateur)
    {
        $this->utilisateur = $utilisateur;

        return $this;
    }

    /**
     * Get utilisateur
     *
     * @return \SFL2\ApplicationBundle\Entity\Utilisateur
     */
    public function getUtilisateur()
    {
        return $this->utilisateur;
    }
    

   
    /**
     * Set article
     *
     * @param \SFL2\ApplicationBundle\Entity\Article $article
     *
     * @return Emprunt
     */
    public function setArticle(\SFL2\ApplicationBundle\Entity\Article $article)
    {
        $this->article = $article;

        return $this;
    }

    /**
     * Get article
     *
     * @return \SFL2\ApplicationBundle\Entity\Article
     */
    public function getArticle()
    {
        return $this->article;
    }

    
    
}
