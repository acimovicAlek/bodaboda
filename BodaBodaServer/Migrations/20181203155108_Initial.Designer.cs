﻿// <auto-generated />
using System;
using BodaBodaServer.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace BodaBodaServer.Migrations
{
    [DbContext(typeof(UserContext))]
    [Migration("20181203155108_Initial")]
    partial class Initial
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "2.1.4-rtm-31024");

            modelBuilder.Entity("BodaBodaServer.Models.Location", b =>
                {
                    b.Property<long>("LocationId")
                        .ValueGeneratedOnAdd();

                    b.Property<double>("Latitude");

                    b.Property<string>("LocationType");

                    b.Property<double>("Longitude");

                    b.Property<long>("TTL");

                    b.Property<long>("UserId");

                    b.HasKey("LocationId");

                    b.HasIndex("UserId")
                        .IsUnique();

                    b.ToTable("Locations");
                });

            modelBuilder.Entity("BodaBodaServer.Models.Payment", b =>
                {
                    b.Property<long>("PaymentId")
                        .ValueGeneratedOnAdd();

                    b.Property<double>("Amount");

                    b.Property<string>("Description");

                    b.Property<long>("PayeeId");

                    b.Property<long>("PayerId");

                    b.Property<string>("Status");

                    b.Property<DateTime>("TimeStamp");

                    b.Property<long>("TripId");

                    b.HasKey("PaymentId");

                    b.HasIndex("PayeeId");

                    b.HasIndex("PayerId");

                    b.HasIndex("TripId");

                    b.ToTable("Payments");
                });

            modelBuilder.Entity("BodaBodaServer.Models.PaymentOption", b =>
                {
                    b.Property<long>("PaymentOptionId")
                        .ValueGeneratedOnAdd();

                    b.Property<string>("Details");

                    b.Property<string>("OptionType");

                    b.Property<long>("UserId");

                    b.HasKey("PaymentOptionId");

                    b.HasIndex("UserId");

                    b.ToTable("PaymentOptions");
                });

            modelBuilder.Entity("BodaBodaServer.Models.TaxiPrice", b =>
                {
                    b.Property<long>("TaxiPriceId")
                        .ValueGeneratedOnAdd();

                    b.Property<double>("PricePerHour");

                    b.Property<double>("PricePerUnit");

                    b.Property<double>("SpecialPrice");

                    b.Property<double>("StartingPrice");

                    b.Property<long>("UserId");

                    b.HasKey("TaxiPriceId");

                    b.HasIndex("UserId")
                        .IsUnique();

                    b.ToTable("TaxiPrices");
                });

            modelBuilder.Entity("BodaBodaServer.Models.Trip", b =>
                {
                    b.Property<long>("TripId")
                        .ValueGeneratedOnAdd();

                    b.Property<long>("CustomerId");

                    b.Property<long>("EndingLocationId");

                    b.Property<bool>("Paid");

                    b.Property<double>("Price");

                    b.Property<long>("StartingLocationId");

                    b.Property<string>("Status");

                    b.Property<long>("TaxiId");

                    b.Property<DateTime>("TripEnd");

                    b.Property<DateTime>("TripStart");

                    b.HasKey("TripId");

                    b.HasIndex("CustomerId");

                    b.HasIndex("EndingLocationId");

                    b.HasIndex("StartingLocationId");

                    b.HasIndex("TaxiId");

                    b.ToTable("Trips");
                });

            modelBuilder.Entity("BodaBodaServer.Models.User", b =>
                {
                    b.Property<long>("UserId")
                        .ValueGeneratedOnAdd();

                    b.Property<string>("Email");

                    b.Property<string>("FirstName");

                    b.Property<string>("LastName");

                    b.Property<string>("Password");

                    b.Property<string>("PhoneNumber");

                    b.Property<string>("UserType");

                    b.Property<string>("Username");

                    b.HasKey("UserId");

                    b.ToTable("Users");
                });

            modelBuilder.Entity("BodaBodaServer.Models.Location", b =>
                {
                    b.HasOne("BodaBodaServer.Models.User", "User")
                        .WithOne("Location")
                        .HasForeignKey("BodaBodaServer.Models.Location", "UserId")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("BodaBodaServer.Models.Payment", b =>
                {
                    b.HasOne("BodaBodaServer.Models.User", "Payee")
                        .WithMany()
                        .HasForeignKey("PayeeId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("BodaBodaServer.Models.User", "Payer")
                        .WithMany()
                        .HasForeignKey("PayerId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("BodaBodaServer.Models.Trip", "Trip")
                        .WithMany()
                        .HasForeignKey("TripId")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("BodaBodaServer.Models.PaymentOption", b =>
                {
                    b.HasOne("BodaBodaServer.Models.User", "User")
                        .WithMany("PaymentOptions")
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("BodaBodaServer.Models.TaxiPrice", b =>
                {
                    b.HasOne("BodaBodaServer.Models.User", "User")
                        .WithOne("TaxiPrice")
                        .HasForeignKey("BodaBodaServer.Models.TaxiPrice", "UserId")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("BodaBodaServer.Models.Trip", b =>
                {
                    b.HasOne("BodaBodaServer.Models.User", "Customer")
                        .WithMany()
                        .HasForeignKey("CustomerId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("BodaBodaServer.Models.Location", "EndingLocation")
                        .WithMany()
                        .HasForeignKey("EndingLocationId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("BodaBodaServer.Models.Location", "StartingLocation")
                        .WithMany()
                        .HasForeignKey("StartingLocationId")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("BodaBodaServer.Models.User", "Taxi")
                        .WithMany()
                        .HasForeignKey("TaxiId")
                        .OnDelete(DeleteBehavior.Cascade);
                });
#pragma warning restore 612, 618
        }
    }
}
